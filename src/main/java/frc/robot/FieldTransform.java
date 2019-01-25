package frc.robot;

import java.util.List;

import frc.util.Bearing;
import frc.util.NavSensor;
import frc.util.Timestamp;
import frc.util.Transform;
import frc.util.Vector;
import frc.vision.TargetInfo;
import frc.vision.TargetTracker;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		return new Transform(0.0,0.0,gyro.getAngleAtTime(t).getTheta());
	}
	
	public Transform getFieldToCamera(Timestamp t) {
		Transform foo2 = getRobotPose(t);
		Transform foo = foo2.transform(new Transform(cameraToRobot.getTranslation().rotateBy(getRobotPose(t).getRotation()), cameraToRobot.getRotation()));
		return foo;
		
	}
	
	/**
	 * Updates the target history stored 
	 */
	public void trackLatestTarget() {
		if(!target.isEmpty()) {
			TargetInfo t = target;
			double x = t.getX();
			double y = t.getY();
			double z = t.getZ();
			
			//Rotate target direction to compensate for camera pitch (rotation matrix)
			double xr = z * camPitch.sin() + x * camPitch.cos();
            double yr = y;
			double zr = z * camPitch.cos() - x * camPitch.sin();
            
            if(zr > 0) {
            	double s = camToGoal / zr;
            	double dist = Math.hypot(xr, yr) * s;
            	Bearing angle = new Bearing(new Vector(xr, yr));
            	Vector targetToCam = new Vector(angle.cos()*dist, angle.sin()*dist);
				
				Timestamp time = new Timestamp(targetsTimestamp);
            	targetHistory.register(time, getFieldToCamera(time).getTranslation().translate(targetToCam.rotateBy(getFieldToCamera(time).getRotation())));
			}
			
		}
	}
	
	public void addVisionTarget(TargetInfo target, double time) {
		this.target = target;
		this.targetsTimestamp = time;
	}

}
