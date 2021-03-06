package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Controls;
import frc.robot.Robot;

/**
 * @purpose Command class for Linear Actuators
 * @author Liam Shearin
 * @version 1/28/2019
 */
public class LinearActuator extends Command {

	double frontLiftSpeed;
	double backLiftSpeed;
	double driveSpeed;

	public LinearActuator() {
		//Sets the required Subsystem
		requires(Robot.linearActuatorBase);

		frontLiftSpeed = .25;
		backLiftSpeed = 0;
		driveSpeed = 0;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {    	
		
		frontLiftSpeed = Controls.getAxis(Controls.LaFrontOutDpad) - Controls.getAxis(Controls.LaFrontInDpad);
		backLiftSpeed = Controls.getAxis(Controls.LaBackOutButton) - Controls.getAxis(Controls.LaBackInButton);
		driveSpeed = Controls.getAxis(Controls.LaDriveForwardButton) - Controls.getAxis(Controls.LaDriveBackwardButton);

		Robot.linearActuatorBase.linearActuator(frontLiftSpeed, backLiftSpeed, driveSpeed);
	}

	//liam code bad
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
