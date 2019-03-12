package frc.robot;

import frc.util.Bearing;
import frc.util.NavSensor;
import frc.util.Timestamp;
import frc.util.Transform;

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

	double targetsTimestamp;
	
	Transform cameraToRobot;
	Bearing camYaw;
	Bearing camPitch;
	double camHeight;
	double camToGoal;
	
	FieldTransform() {
		cameraToRobot = new Transform(Constants.kCameraXOffset, 
										Constants.kCameraYOffset,
										Constants.kCameraYawOffset);
		camPitch = new Bearing(Constants.kCameraPitchOffset);
		camYaw = new Bearing(Constants.kCameraYawOffset);
		camHeight = Constants.kCameraZOffset;
		camToGoal = Constants.kGoalHeight - camHeight;
	}
	
	public Transform getRobotPose(Timestamp t) {
		return new Transform(0.0,0.0,gyro.getAngleAtTime(t).getTheta());
	}
	
	public Transform getFieldToCamera(Timestamp t) {
		return getRobotPose(t).transform(new Transform(cameraToRobot.getTranslation().rotateBy(getRobotPose(t).getRotation()), cameraToRobot.getRotation()));
		
	}

}
