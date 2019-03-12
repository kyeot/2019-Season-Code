package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.autonomous.ActionScheduler;
import frc.robot.Constants;
import frc.robot.Controls;
import frc.robot.Robot;

/**
 * @purpose Command class for Elevator
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class Elevator extends Command {

	public static double speed;
	public boolean foo;

	public Elevator() {
		//Sets the required Subsystem
		requires(Robot.elevatorBase);

		speed = 0;
		foo = false;
  
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {    	
		
		speed = Controls.getAxis(Controls.ElevatorUpAxis, 0.1) - Controls.getAxis(Controls.ElevatorDownAxis, 0.1)*Constants.kElevatorDownSpeed;

		Robot.elevatorBase.elevator(speed);
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
