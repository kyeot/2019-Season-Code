package frc.robot;

/**
 * 
 * Class used to set every constant variable
 *
 */
public class Constants {

	/*
		PID's
	*/
	
	public static final double kSwerveRotP = 0.0015;
	public static final double kSwerveRotI = 0.00; 
	public static final double kSwerveRotD = 0.001;
	
	public static final double kSwerveP = 0.03;
	public static final double kSwerveI = 0.0;
	public static final double kSwerveD = 0.007;

	public static final double kElevateP = 0.01;
	public static final double kElevateI = 0.0;
	public static final double kElevateD = 0.0;
	
	/* 
		CONTROLLER ID'S
	*/

	public static final int kDriverControllerId = 0;
	public static final int kManipulatorControllerId = 1;

	// Driver
	public static final int kFBAxisId = 1;
	public static final int kRLAxisId = 0;
	public static final int kRotAxisId = 4;
	public static final int kHalfSpeedButtonId = 5;
	public static final int kSprintButtonId = 9;	
	public static final int kCenterGyroButtonId = 4;
	public static final int kZeroModulesButtonId = 3;
	public static final int kDockingModeButtonId = 6;

	public static final double halfSpeedSpeed = 0.7;
	public static final double normalSpeedSpeed = 0.5;
	public static final double fastSpeedSpeed = 2;

	// Manipulator
	public static final int kElevatorUpAxisId = 3;
	public static final int kElevatorDownAxisId = 2;
	public static final int kIntakeAxisId = 5;
	public static final int kAutoElevateAndSpitButtonId = 1;
	public static final int kReleaseHatchButtonId = 2;
	public static final int kZeroElevatorButtonId = 4;
	public static final int kIntakeInButtonId = 7;
	public static final int kIntakeOutButtonId = 8;
	public static final int kServoReleaseButtonId = 3;
	public static final int kServoRelease2ButtonId = 8;
	public static final int kLAFrontOutDpadId = 0;
	public static final int kLAFrontInDpadId = 180;
	public static final int kLABackOutButtonId = 4;
	public static final int kLABackInButtonId = 1;
	public static final int kLADriveForwardButtonId = 5;
	public static final int kLADriveBackwardButtonId = 6;

	public static final double kElevatorDownSpeed = 0.8; 
	public static final double kIntakeSpeed = 0.75;


	/*
		MOTOR ID'S
	*/

	public static final int kFrontLeftWheelId = 15;        // Victor
	public static final int kFrontRightWheelId = 9;        // Victor
	public static final int kRearLeftWheelId = 12;         // Victor
	public static final int kRearRightWheelId = 7;         // Victor
	
	public static final int kFrontLeftSwivelId = 13;       // Victor
	public static final int kFrontRightSwivelId = 10;      // Victor
	public static final int kRearLeftSwivelId = 11;        // Victor
	public static final int kRearRightSwivelId = 6;        // Victor

	public static final int kElevatorId = 3;               // Talon
	public static final int kIntakeId = 2;                 // Talon

	public static final int kFrontLinearActuatorId = 0;    // Talon
	public static final int kBackLinearActuatorId = 4;     // Talon
	public static final int kLinearActuatorDriveMotId = 1; // Talon

	
	/*
		MODULE OFFSETS
	*/

	// Practice
	public static final double kFrontLeftAngleOffset = 144;
	public static final double kFrontRightAngleOffset = 9;
	public static final double kRearLeftAngleOffset = 244;
	public static final double kRearRightAngleOffset = 136;

	// Competition
	// public static final double kFrontLeftAngleOffset = 140;
	// public static final double kFrontRightAngleOffset = 95;
	// public static final double kRearLeftAngleOffset = 301;
	// public static final double kRearRightAngleOffset = 190;

	
	/*
		ENCODER ID'S (Analog Input ports)
	*/

	public static final int kFrontLeftAbsoluteEncoder = 0;
	public static final int kFrontRightAbsoluteEncoder = 1;
	public static final int kRearLeftAbsoluteEncoder = 3;
	public static final int kRearRightAbsoluteEncoder = 2;

	/*
		VISION
	*/

	public static final double kCameraXOffset = 0.0;
	public static final double kCameraYOffset = 0.0;
	public static final double kCameraZOffset = 0.0;

	public static final double kCameraPitchOffset = 0.0;
	public static final double kCameraYawOffset = 0.0;

	public static final double kGoalHeight = 0.0;

	/*
		MISC
	*/

	public static final double kLoopPeriod = 0.01;                   // Amount of time(s) the loop notifier runs in
	public static final double kActionPeriod = 0.01;                 // Amount of time(s) the action notifier runs in;

	public static final double kRobotFront = 180;                    // Angle Offset to Front of Robot from NavX
	public static final double kGyroResetAngle = 0;
	public static final double kRobotStartAngle = 90;

	public static final int kServoId = 0;                            // Latch Servo Port (PWM)

	public static final double kServoLatchedAngle = 0;
	public static final double kServoUnlatchedAngle = 180;

	public static final double kElevatorEncRange = (850-155)/2;

	public static final double kAnalogInputToDegreeRatio = 4096/360; // Amount of degrees per tick of an absolute encoder

	public static final double kGyroMaxAge = 0.6;                    // Length(s) of angle history stored in NavSensor            

	public static final double kSwerveRampRate = 0.0;                // Sets the open-loop ramp rate for swerve drive in seconds
	public static final double kElevatorRampRate = 0.2;

	public static final double kEventDelay = 3;                      // Time(s) the event logger will wait before logging the same message
	
	public static final double kTooFast = 0.5;                       // Max output for pose pid before it stops
}
