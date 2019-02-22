package frc.robot;

public enum Controls {
    
    enum Controller {
        DRIVER(OI.driver),
        MANIPULATOR(OI.manipulator);

        Joystick joystick;
        
        public Controller(Joystick joystick) {
            this.joystick = joystick;
        }

        public Joystick getJoystick() {
            return joystick;
        }
    }

    enum ControlType {
        BUTTON,
        AXIS,
        DPAD;
    }

    FB_AXIS(Constants.kFBAxisId, Controller.DRIVER, ControlType.AXIS),
    RL_AXIS(Constants.kRLAxisId, Controller.DRIVER, ControlType.AXIS),
    ROT_AXIS(Constants.kRotAxisId, Controller.DRIVER, ControlType.AXIS),
    HALF_SPEED_BUTTON(Constants.kHalfSpeedButtonId, Controller.DRIVER, ControlType.BUTTON),
    SPRINT_BUTTON(Constants.kSprintButtonId, Controller.DRIVER, ControlType.BUTTON),
    CENTER_GYRO_BUTTON(Constants.kCenterGyroButtonId, Controller.DRIVER, ControlType.BUTTON),
    ZERO_MODULES_BUTTON(Constants.kZeroModulesButtonId, Controller.DRIVER, ControlType.BUTTON),
    DOCKING_MODE_BUTTON(Constants.kDockingModeButtonId, Controller.DRIVER, ControlType.BUTTON),

    LA_OUT_DPAD(Constants.kLAOutButtonId, Controller.MANIPULATOR, ControlType.DPAD),
    LA_IN_DPAD(Constants.kLAInButtonId, Controller.MANIPULATOR, ControlType.DPAD),
    LA_DRIVE_FORWARD_DPAD(Constants.kLADriveForwardButtonId, Controller.MANIPULATOR, ControlType.DPAD),
    LA_DRIVE_BACKWARD_DPAD(Constants.kLADriveBackwardButtonId, Controller.MANIPULATOR, ControlType.DPAD),
    LA_AUTO_BUTTON(Constants.kAutoLinearActuatorButtonId, Controller.MANIPULATOR, ControlType.BUTTON),
    INTAKE_IN_BUTTON(Constants.kIntakeIn, Controller.MANIPULATOR, ControlType.BUTTON),
    INTAKE_OUT_BUTTON(Constants.kIntakeOut, Controller.MANIPULATOR, ControlType.BUTTON),
    ELEVATE_AND_SPIT_BUTTON(Constants.kAutoElevateAndSpitButtonId, Controller.MANIPULATOR, ControlType.BUTTON),
    ELEVATOR_UP_AXIS(Constants.kElevatorUpAxis, Controller.MANIPULATOR, ControlType.AXIS),
    ELEVATOR_DOWN_AXIS(Constants.kElevatorDownAxis, Controller.MANIPULATOR, ControlType.AXIS);

    int id;
    Controller controller;
    ControlType type;

    public Controls(int id, Controller controller, ControlType type) {
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

    public static boolean getButton(DriverControls control, double deadband = 0.0) {
        if(control.getType() == ControlType.BUTTON) {
            return control.getController().getJoystick().getRawButton(control.getId());
        } else if(control.getType() == ControlType.DPAD) {
            return control.getController().getJoystick().getPOV() == control.getId();
        } else if(control.getType() == ControlType.AXIS) {
            return Math.abs(control.getController().getJoystick().getRawAxis(control.getId())) > deadband;
        }
    }

    public static double getAxis(DriverControls control, double deadband = 0.0) {
        if(control.getType() == ControlType.BUTTON) {
            return control.getController().getJoystick().getRawButton(control.getId()) ? 1.0 : 0.0;
        } else if(control.getType() == ControlType.DPAD) {
            return control.getController().getJoystick().getPOV() == control.getId() ? 1.0 : 0.0;
        } else if(control.getType() == ControlType.AXIS) {
            return Math.abs(control.getController().getJoystick().getRawAxis(control.getId())) > deadband ? control.getController().getJoystick().getRawAxis(control.getId()) : 0.0;
        }
    }


}