package frc.robot.commands;

import org.usfirst.frc2783.robot.Constants;
import org.usfirst.frc2783.robot.OI;
import org.usfirst.frc2783.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @purpose Command class for Linear Actuators
 * @author Liam Shearin
 * @version 1/28/2019
 */
public class LinearActuator extends Command {
	
    public LinearActuator() {
    	//Sets the required Subsystem
        requires(Robot.LinearActuatorBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	
    	double speed = OI.manipulator.getRawButton(4);
    	
    	
    	if(OI.manipulator.getRawButton(Constants.kLinearActuatorId)){
    		Robot.linearActuator.spinAdjust(speed);
    	}
    	else{
    		Robot.linearActuator.linearActuator(-speed);
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
