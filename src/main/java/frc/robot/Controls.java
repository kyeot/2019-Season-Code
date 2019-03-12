package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public enum Controls {

	FbAxis(Constants.kFBAxisId, Controller.Driver, ControlType.Axis),
	RlAxis(Constants.kRLAxisId, Controller.Driver, ControlType.Axis),
	RotAxis(Constants.kRotAxisId, Controller.Driver, ControlType.Axis),
	HalfSpeedButton(Constants.kHalfSpeedButtonId, Controller.Driver, ControlType.Button),
	SprintButton(Constants.kSprintButtonId, Controller.Driver, ControlType.Button),
	CenterGyroButton(Constants.kCenterGyroButtonId, Controller.Driver, ControlType.Button),
	ZeroModulesButton(Constants.kZeroModulesButtonId, Controller.Driver, ControlType.Button),
	DockingModeButton(Constants.kDockingModeButtonId, Controller.Driver, ControlType.Button),

	LaFrontOutDpad(Constants.kLAFrontOutDpadId, Controller.Manipulator, ControlType.Dpad),
	LaFrontInDpad(Constants.kLAFrontInDpadId, Controller.Manipulator, ControlType.Dpad),
	LaBackOutDpad(Constants.kLABackOutButtonId, Controller.Manipulator, ControlType.Dpad),
	LaBackInDpad(Constants.kLABackInButtonId, Controller.Manipulator, ControlType.Dpad),
	LaDriveForwardButton(Constants.kLADriveForwardButtonId, Controller.Manipulator, ControlType.Button),
	LaDriveBackwardButton(Constants.kLADriveBackwardButtonId, Controller.Manipulator, ControlType.Button),
	LaAutoButton(Constants.kAutoLinearActuatorButtonId, Controller.Manipulator, ControlType.Button),
	IntakeInButton(Constants.kIntakeInButtonId, Controller.Manipulator, ControlType.Button),
	IntakeOutButton(Constants.kIntakeOutButtonId, Controller.Manipulator, ControlType.Button),
	ElevateAndSpitButton(Constants.kAutoElevateAndSpitButtonId, Controller.Manipulator, ControlType.Button),
	ElevatorUpAxis(Constants.kElevatorUpAxisId, Controller.Manipulator, ControlType.Axis),
	ElevatorDownAxis(Constants.kElevatorDownAxisId, Controller.Manipulator, ControlType.Axis),
	ServoRelease(Constants.kServoReleaseButtonId, Controller.Manipulator, ControlType.Button),
	IntakeAxis(Constants.kIntakeAxisId, Controller.Manipulator, ControlType.Axis),
	ZeroElevator(Constants.kZeroElevatorButtonId, Controller.Manipulator, ControlType.Button);
	
	public enum Controller {
		Driver(OI.driver),
		Manipulator(OI.manipulator);

		Joystick joystick;
		
		private Controller(Joystick joystick) {
			this.joystick = joystick;
		}

		public Joystick getJoystick() {
			return joystick;
		}
	}

	public enum ControlType {
		Button,
		Axis,
		Dpad;
	}

	int id;
	Controller controller;
	ControlType type;

	private Controls(int id, Controller controller, ControlType type) {
		this.id = id;
		this.controller = controller;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public Controller getController() {
		return controller;
	}

	public ControlType getType() {
		return type;
	}


	public static boolean getButton(Controls control) {
		return getButton(control, 0.0);
	}

	public static boolean getButton(Controls control, double deadband) {
		if(control.getType() == ControlType.Button) {
			return control.getController().getJoystick().getRawButton(control.getId());
		} else if(control.getType() == ControlType.Dpad) {
			return control.getController().getJoystick().getPOV() == control.getId();
		} else if(control.getType() == ControlType.Axis) {
			return Math.abs(control.getController().getJoystick().getRawAxis(control.getId())) > deadband;
		}
		return false;
	}

	public static double getAxis(Controls control) {
		return getAxis(control, 0.0);
	}

	public static double getAxis(Controls control, double deadband) {
		if(control.getType() == ControlType.Button) {
			return control.getController().getJoystick().getRawButton(control.getId()) ? 1.0 : 0.0;
		} else if(control.getType() == ControlType.Dpad) {
			return control.getController().getJoystick().getPOV() == control.getId() ? 1.0 : 0.0;
		} else if(control.getType() == ControlType.Axis) {
			return Math.abs(control.getController().getJoystick().getRawAxis(control.getId())) > deadband ? control.getController().getJoystick().getRawAxis(control.getId()) : 0.0;
		}
		return 0.0;
	}


}