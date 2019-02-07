package frc.robot;

import java.util.List;

import frc.util.Bearing;
import frc.util.NavSensor;
import frc.util.Timestamp;
import frc.util.Transform;
import frc.util.Vector;
import frc.vision.TargetInfo;
import frc.vision.TargetTracker;

/**
 * Singleton class used to get useful Transformations of things tracked on the field,
 *  i.e. the robot or vision targets
 * 
 * @author 2783
 */
public class FieldTransform {
	public static FieldTransform fieldTransform = new FieldTransform();
	
	public static FieldTransform getInstance() {
		return fieldTransform;
	}
	
	NavSensor gyro = NavSensor.getInstance();
	
	TargetInfo target;
	double targetsTimestamp;
	
	public TargetTracker targetHistory;
	
	Transform cameraToRobot;
	Bearing camYaw;
	Bearing camPitch;
	double camHeight;
	double camToGoal;
	
	FieldTransform() {
		cameraToRobot = new Transform(Constants.kCameraXOffset, 
										Constants.kCameraYOffset,
										Constants.kCameraYawOffset);
		camPitch = new Bearing(-Constants.kCameraPitchOffset);
		camHeight = Constants.kCameraZOffset;
		camToGoal = Constants.kGoalHeight - camHeight;
		targetHistory = new TargetTracker();
	}
	
	public Transform getRobotPose(Timestamp t) {
		Bearing foo = gyro.getAngleAtTime(t);
		return new Transform(0.0,0.0,foo.getTheta());
	}
	
	public Transform getFieldToCamera(Timestamp t) {
		return getRobotPose(t).transform(new Transform(cameraToRobot.getTranslation().rotateBy(getRobotPose(t).getRotation()), cameraToRobot.getRotation()));
		
	}
	
	/**
	 * Updates the target history stored 
	 */
	public void trackLatestTarget() {
		TargetInfo t = target;
		double x = t.getX();
		double y = t.getY();
		double z = t.getZ();
		
		//Rotate target direction to compensate for camera pitch (rotation matrix)
		double xr = z * camPitch.sin() + x * camPitch.cos();
		double yr = y;
		double zr = z * camPitch.cos() - x * camPitch.sin();
		
		double s = camToGoal / zr;
		double dist = Math.hypot(xr, yr) * s;
		Bearing angle = new Bearing(new Vector(xr, yr));
		Vector targetToCam = new Vector(angle.cos()*dist, angle.sin()*dist);

		Timestamp time = new Timestamp(target.getTime());
		targetHistory.register(time, getFieldToCamera(time).getTranslation().translate(targetToCam.rotateBy(getFieldToCamera(time).getRotation())));
	}
	
	public void addVisionTarget(TargetInfo target) {
		this.target = target;
	}

}
