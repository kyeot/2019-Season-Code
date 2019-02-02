package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.autonomous.actiongroups.*;

/**
 * @purpose Command class for Linear Actuator Drive Motor
 * @author Liam Shearin
 * @version 1/28/2019
 */
public class LaDrive extends Command {
	
    public LaDrive() {
    	//Sets the required Subsystem
        requires(Robot.laDriveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	if(OI.manipulator.getRawButton(Constants.kLinearActuatorDriveForward)) {
    		Robot.laDriveBase.laDrive(1);
        }
        else if(OI.manipulator.getRawButton(Constants.kLinearActuatorDriveBackward)) {
            Robot.laDriveBase.laDrive(-1);
        }
        else if (OI.manipulator.getRawButton(Constants.kAutoLinearActuator)) {
            Robot.actionScheduler.wipe();
            Robot.actionScheduler.setGroup(new LinearActuatorGroup());
            Robot.actionScheduler.start();
        }
   /*     else{
    		Robot.linearActuatorBase.linearActuator(.1);
 
        }
     */   
    }

    // Make this return true when this Command no longer needs to run execute()
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
