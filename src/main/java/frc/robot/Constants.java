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


	//Controller IDs
	public static final int kDriverControllerId = 0;
	public static final int kManipulatorControllerId = 1;


	//Manipulator Buttons 
	public static final int kLAOutButtonId = 0;
	public static final int kLAInButtonId = 180;
	public static final int kLADriveForwardButtonId = 90;
	public static final int kLADriveBackwardButtonId = 270;
	public static final int kAutoLinearActuatorButtonId = 2; 
	public static final int kElevatorDown = 5;
	public static final int kElevatorUp = 6;

	public static final int kAutoElevateAndSpitButtonId = 1;


	//Motor Controller IDs
	public static final int kFrontLeftWheelId = 10;
	public static final int kFrontRightWheelId = 11;
	public static final int kRearLeftWheelId = 12;
	public static final int kRearRightWheelId = 13;
	
	public static final int kFrontLeftSwivelId = 15;
	public static final int kFrontRightSwivelId = 6;
	public static final int kRearLeftSwivelId = 7;
	public static final int kRearRightSwivelId = 9;

	public static final int kLinearActuatorDriveMotId = 0;
	public static final int kFrontLinearActuatorId = 1;
	public static final int kBackLinearActuatorId = 2;
	public static final int kElevatorId = 3;
	public static final int kIntakeId = 4;


	public static final double kAnalogInputToDegreeRatio = 4096/360;

	public static final double kFrontLeftAngleOffset = 360 - 87.2;
	public static final double kFrontRightAngleOffset = 360 - 16.8;
	public static final double kRearLeftAngleOffset = 360 - 8.6;
	public static final double kRearRightAngleOffset = 360 - 69.3;

	
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

	public static int kLinearActuatorID;

}
