package frc.robot;

/**
 * 
 * Class used to set every constant variable
 *
 */
public class Constants {
	
	public static final double kPeriod = 0.01;
	
	public static final double kSwerveP = 0.02;
	public static final double kSwerveI = 0.0; 
	public static final double kSwerveD = 0.005;

	public static final double kSwerveRotP = 0.0015;
	public static final double kSwerveRotI = 0.0; 
	public static final double kSwerveRotD = 0.001;
	
	public static final double kAngleToEncoderTick = 0.875;

	public static final double kGyroMaxAge = 0.6;
	
	public static final int kDriverControllerId = 0;
	public static final int kManipulatorControllerId = 1;

	public static final int kLAOutButtonId = 0;
	public static final int kLAInButtonId = 180;
	public static final int kLADriveForwardButtonId = 90;
	public static final int kLADriveBackwardButtonId = 270;
	public static final int kAutoLinearActuatorButtonId = 2;
	public static final int kIntakeIn = 5;
	public static final int kIntakeOut = 6;
	public static final int kAutoElevateAndSpitButtonId = 1;
	public static final int kElevatorUpAxis = 3;
	public static final int kElevatorDownAxis = 2;


	public static final int kElevatorId = 3;
	public static final int kIntakeId = 2; 

	public static final int kFrontLeftWheelId = 15;
	public static final int kFrontRightWheelId = 9;
	public static final int kRearLeftWheelId = 12;
	public static final int kRearRightWheelId = 7; 
	
	public static final int kFrontLeftSwivelId = 13;
	public static final int kFrontRightSwivelId = 10;
	public static final int kRearLeftSwivelId = 11;
	public static final int kRearRightSwivelId = 6; 

	public static final int kFrontLinearActuatorId = 0;
	public static final int kBackLinearActuatorId = 4;
	public static final int kLinearActuatorDriveMotId = 1;


	public static final int kServoId = 0;

	public static final double kAnalogInputToDegreeRatio = 4096/360;

	
	//practice
	//35
	//328
	//244
	//136

	public static final double kFrontLeftAngleOffset = 360 - 35;
	public static final double kFrontRightAngleOffset = 360 - 328;
	public static final double kRearLeftAngleOffset = 360 - 244;
	public static final double kRearRightAngleOffset = 360 - 136;
	
	public static final int kFrontLeftAbsoluteEncoder = 0;
	public static final int kFrontRightAbsoluteEncoder = 1;
	public static final int kRearLeftAbsoluteEncoder = 3;
	public static final int kRearRightAbsoluteEncoder = 2;

	public static final int kFrontLeftEncoderA = 4;
	public static final int kFrontLeftEncoderB = 5;
	public static final int kFrontRightEncoderA = 0;
	public static final int kFrontRightEncoderB = 1;
	public static final int kRearLeftEncoderA = 6;
	public static final int kRearLeftEncoderB = 7;
	public static final int kRearRightEncoderA = 2;
	public static final int kRearRightEncoderB = 3;

}
