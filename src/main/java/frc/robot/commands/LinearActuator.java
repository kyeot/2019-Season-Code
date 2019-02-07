package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;
import frc.autonomous.ActionScheduler;
import frc.autonomous.actiongroups.*;

/**
 * @purpose Command class for Linear Actuators
 * @author Liam Shearin
 * @version 1/28/2019
 */
public class LinearActuator extends Command {

    public static double liftFrontSpeed;
    public static double liftBackSpeed;
    public static double driveSpeed;

    public LinearActuator() {
    	//Sets the required Subsystem
        requires(Robot.linearActuatorBase);

        liftFrontSpeed = 0;
        liftBackSpeed = 0;
        driveSpeed = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
        
        liftFrontSpeed = 0;
        liftBackSpeed = 0;
        driveSpeed = 0;
        
    	if(OI.manipulator.getPOV() == Constants.kLAOutButtonId) {
            liftFrontSpeed = 1;
            liftBackSpeed = 1;
        }
        else if(OI.manipulator.getPOV() == Constants.kLAInButtonId) {
           liftFrontSpeed = -1;
           liftBackSpeed = -1;
            
        }
            
        if(OI.manipulator.getPOV() == Constants.kLADriveForwardButtonId) {
    		driveSpeed = 1;
        }
        else if(OI.manipulator.getPOV() == Constants.kLADriveBackwardButtonId) {
            driveSpeed = -1;
        }

        if (OI.manipulator.getRawButton(Constants.kAutoLinearActuatorButtonId)) {
            ActionScheduler.getInstance().setGroup(new LinearActuatorGroup());
            ActionScheduler.getInstance().start();
        }
        else{
            if(!ActionScheduler.getInstance().isActive()){
                Robot.linearActuatorBase.linearActuator(liftFrontSpeed, liftBackSpeed, driveSpeed);
            }
        }
    }

    // Make this return true when this Command no
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
