package frc.robot;

/**
 * 
 * Class used to set every constant variable
 *
 */
public class Constants {
	
	public static final double kPeriod = 0.01;
	
	//public static final double kSwerveP = 0.022;
	//public static final double kSwerveI = 0.04; 
	//public static final double kSwerveD = 0.025;
	public static final double kSwerveP = 0.01;
	public static final double kSwerveI = 0.0; 
	public static final double kSwerveD = 0.02;
	
	public static final double kAngleToEncoderTick = 0.875;
	
	public static final int kDriverControllerId = 0;
	public static final int kManipulatorControllerId = 1;
	
	public static final int kFrontLeftWheelId = 862;
	public static final int kFrontRightWheelId = 2;
	public static final int kRearLeftWheelId = 118;
	public static final int kRearRightWheelId = 148;
	
	public static final int kFrontLeftSwivelId = 2783;
	public static final int kFrontRightSwivelId = 0;
	public static final int kRearLeftSwivelId = 16;
	public static final int kRearRightSwivelId = 254;

	public static final double kAnalogInputToDegreeRatio = 4096/360;

	public static final double kFrontLeftAngleOffset = 0;
	public static final double kFrontRightAngleOffset = 345;
	public static final double kRearLeftAngleOffset = 0;
	public static final double kRearRightAngleOffset = 0;
	
	public static final int kFrontLeftAbsoluteEncoder = 0;
	public static final int kFrontRightAbsoluteEncoder = 1;
	public static final int kRearLeftAbsoluteEncoder = 2;
	public static final int kRearRightAbsoluteEncoder = 3;

	public static final int kFrontLeftEncoderA = 4;
	public static final int kFrontLeftEncoderB = 5;
	public static final int kFrontRightEncoderA = 0;
	public static final int kFrontRightEncoderB = 1;
	public static final int kRearLeftEncoderA = 6;
	public static final int kRearLeftEncoderB = 7;
	public static final int kRearRightEncoderA = 2;
	public static final int kRearRightEncoderB = 3;

}
