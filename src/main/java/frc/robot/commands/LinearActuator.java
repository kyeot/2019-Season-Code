package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @purpose Command class for Linear Actuators
 * @author Liam Shearin
 * @version 1/28/2019
 */
public class LinearActuator extends Command {
	
    public LinearActuator() {
    	//Sets the required Subsystem
        requires(Robot.linearActuatorBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	if(OI.manipulator.getRawButton(Constants.kLinearActuatorOut)) {
    		Robot.linearActuatorBase.linearActuator(1);
        }
        else if(OI.manipulator.getRawButton(Constants.kLinearActuatorIn)) {
            Robot.linearActuatorBase.linearActuator(-.5);
        }
    	else{
    		Robot.linearActuatorBase.linearActuator(.1);
 
        }
            
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
