package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public enum Controls {

    FB_AXIS(Constants.kFBAxisId, Controller.DRIVER, ControlType.AXIS),
    RL_AXIS(Constants.kRLAxisId, Controller.DRIVER, ControlType.AXIS),
    ROT_AXIS(Constants.kRotAxisId, Controller.DRIVER, ControlType.AXIS),
    HALF_SPEED_BUTTON(Constants.kHalfSpeedButtonId, Controller.DRIVER, ControlType.BUTTON),
    SPRINT_BUTTON(Constants.kSprintButtonId, Controller.DRIVER, ControlType.BUTTON),
    CENTER_GYRO_BUTTON(Constants.kCenterGyroButtonId, Controller.DRIVER, ControlType.BUTTON),
    ZERO_MODULES_BUTTON(Constants.kZeroModulesButtonId, Controller.DRIVER, ControlType.BUTTON),
    DOCKING_MODE_BUTTON(Constants.kDockingModeButtonId, Controller.DRIVER, ControlType.BUTTON),

    LA_OUT_DPAD(Constants.kLAOutDpadId, Controller.MANIPULATOR, ControlType.DPAD),
    LA_IN_DPAD(Constants.kLAInDpadId, Controller.MANIPULATOR, ControlType.DPAD),
    LA_DRIVE_FORWARD_DPAD(Constants.kLADriveForwardDpadId, Controller.MANIPULATOR, ControlType.DPAD),
    LA_DRIVE_BACKWARD_DPAD(Constants.kLADriveBackwardDpadId, Controller.MANIPULATOR, ControlType.DPAD),
    LA_AUTO_BUTTON(Constants.kAutoLinearActuatorButtonId, Controller.MANIPULATOR, ControlType.BUTTON),
    INTAKE_IN_BUTTON(Constants.kIntakeInButtonId, Controller.MANIPULATOR, ControlType.BUTTON),
    INTAKE_OUT_BUTTON(Constants.kIntakeOutButtonId, Controller.MANIPULATOR, ControlType.BUTTON),
    ELEVATE_AND_SPIT_BUTTON(Constants.kAutoElevateAndSpitButtonId, Controller.MANIPULATOR, ControlType.BUTTON),
    ELEVATOR_UP_AXIS(Constants.kElevatorUpAxisId, Controller.MANIPULATOR, ControlType.AXIS),
    ELEVATOR_DOWN_AXIS(Constants.kElevatorDownAxisId, Controller.MANIPULATOR, ControlType.AXIS);
    
    public enum Controller {
        DRIVER(OI.driver),
        MANIPULATOR(OI.manipulator);

        Joystick joystick;
        
        private Controller(Joystick joystick) {
            this.joystick = joystick;
        }

        public Joystick getJoystick() {
            return joystick;
        }
    }

    public enum ControlType {
        BUTTON,
        AXIS,
        DPAD;
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
        if(control.getType() == ControlType.BUTTON) {
            return control.getController().getJoystick().getRawButton(control.getId());
        } else if(control.getType() == ControlType.DPAD) {
            return control.getController().getJoystick().getPOV() == control.getId();
        } else if(control.getType() == ControlType.AXIS) {
            return Math.abs(control.getController().getJoystick().getRawAxis(control.getId())) > deadband;
        }
        return false;
    }

    public static double getAxis(Controls control) {
        return getAxis(control, 0.0);
    }

    public static double getAxis(Controls control, double deadband) {
        if(control.getType() == ControlType.BUTTON) {
            return control.getController().getJoystick().getRawButton(control.getId()) ? 1.0 : 0.0;
        } else if(control.getType() == ControlType.DPAD) {
            return control.getController().getJoystick().getPOV() == control.getId() ? 1.0 : 0.0;
        } else if(control.getType() == ControlType.AXIS) {
            return Math.abs(control.getController().getJoystick().getRawAxis(control.getId())) > deadband ? control.getController().getJoystick().getRawAxis(control.getId()) : 0.0;
        }
        return 0.0;
    }


}