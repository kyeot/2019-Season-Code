package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	
	Transform robotToCamera;
	Bearing camYaw;
	Bearing camPitch;
	double camHeight;
	double camToGoal;
	
	FieldTransform() {
		robotToCamera = new Transform(Constants.kCameraXOffset, 
										Constants.kCameraYOffset,
										Constants.kCameraYawOffset);
		camPitch = new Bearing(Constants.kCameraPitchOffset);
		camYaw = new Bearing(Constants.kCameraYawOffset);
		camHeight = Constants.kCameraZOffset;
		camToGoal = Constants.kGoalHeight - camHeight;
		targetHistory = new TargetTracker();
	}
	
	public Transform getRobotPose(Timestamp t) {
		Bearing foo = gyro.getAngleAtTime(t);
		return new Transform(0.0,0.0,foo.getTheta());
	}
	
	public Transform getFieldToCamera(Timestamp t) {
		return getRobotPose(t).transform(new Transform(robotToCamera.getTranslation().rotateBy(getRobotPose(t).getRotation()), robotToCamera.getRotation()));
		
	}
	
	/**
	 * Updates the target history stored 
	 */
	public void trackLatestTarget() {
		TargetInfo t = target;
		double x = t.getX();
		double y = t.getY();
		double z = t.getZ();

		//Rotation matrices (right-handed system)
		//Pitch (Rz)
	    double xp = x*camPitch.cos() - y*camPitch.sin();
		double yp = x*camPitch.sin() + y*camPitch.cos();
		double zp = z;

		//Yaw (Ry)
		double xy =  xp*camYaw.cos() + zp*camYaw.sin();
		double yy =  yp;
		double zy = -xp*camYaw.sin() + zp*camYaw.cos();
		 
		//Right-Handed -> Flat-Field(x is right, y is forward, z is up)
	    double xf = xy;
		double yf = zy;
		double zf = yy;
		
		double s = camToGoal / zf;
		double dist = Math.hypot(xf, yf) * s;
		Bearing angle = new Bearing(new Vector(xf, yf));
		Vector cameraToTarget = new Vector(angle.cos()*dist, angle.sin()*dist);

		Timestamp time = new Timestamp(target.getTime());
		targetHistory.register(time, getFieldToCamera(time).getTranslation().translate(cameraToTarget.rotateBy(getFieldToCamera(time).getRotation())));
	}
	
	public void addVisionTarget(TargetInfo target) {
		this.target = target;
	}

}
