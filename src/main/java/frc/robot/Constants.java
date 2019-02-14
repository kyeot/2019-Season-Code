package frc.robot;

/**
 * 
 * Class used to set every constant variable
 *
 */
public class Constants {
	
	public static final double kPeriod = 0.01;
	
	public static final double kSwerveModP = 0.022;
	public static final double kSwerveModI = 0.04; 
	public static final double kSwerveModD = 0.025;
	
	public static final double kSwerveP = 0.02;
	public static final double kSwerveI = 0.000;
	public static final double kSwerveD = 0.00;

	public static final double kSwerveAlignP = 2;
	public static final double kSwerveAlignI = 0.0;
	public static final double kSwerveAlignD = 0.0;

	public static final double kTooFast = 0.5; //max output for pid before it stops
	
	public static final double kAngleToEncoderTick = 0.875;
		
	public static final double kEventDelay = 4;
	
	public static final int kAndroidAppTcpPort = 8254;
	
	public static final double kCameraFrameRate = 30;
	public static final double kTargetMaxAge = 0.4; //seconds, time it smooths over
	
	public static final double kGyroMaxAge = 0.3;
	
	public static final double kCameraXOffset = 10.75;
	public static final double kCameraYOffset = -16.25;
	public static final double kCameraZOffset = 23.5;
	public static final double kCameraPitchOffset = 0;
	public static final double kCameraRollOffset = 0.0;
	public static final double kCameraYawOffset = 0.0;
	
	public static final double kGoalHeight = 28.75;
	
	public static final double kRobotFront = 90;
	
	public static final int kDriverControllerId = 0;
	
	public static final int kFrontLeftWheelId = 2;
	public static final int kFrontRightWheelId = 0;
	public static final int kRearLeftWheelId = 3;
	public static final int kRearRightWheelId = 1;
	
	public static final int kFrontLeftSwivelId = 2;
	public static final int kFrontRightSwivelId = 0;
	public static final int kRearLeftSwivelId = 3;
	public static final int kRearRightSwivelId = 1;
	
	public static final int kFrontLeftEncoderA = 4;
	public static final int kFrontLeftEncoderB = 5;
	public static final int kFrontRightEncoderA = 0;
	public static final int kFrontRightEncoderB = 1;
	public static final int kRearLeftEncoderA = 6;
	public static final int kRearLeftEncoderB = 7;
	public static final int kRearRightEncoderA = 2;
	public static final int kRearRightEncoderB = 3;

}
