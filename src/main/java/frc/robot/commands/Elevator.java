package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.autonomous.ActionScheduler;
import frc.autonomous.actiongroups.PickupHatch;
import frc.autonomous.actiongroups.ReleaseHatch;
import frc.robot.Constants;
import frc.robot.Controls;
import frc.robot.Robot;

/**
 * @purpose Command class for Elevator
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class Elevator extends Command {

	double speed;
	boolean bPressedLast;
	boolean xPressedLast;

	public Elevator() {
		//Sets the required Subsystem
		requires(Robot.elevatorBase);

		speed = 0;
		bPressedLast = false;
		xPressedLast = false;
  
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {    	
		
		speed = Controls.getAxis(Controls.ElevatorUpAxis, 0.1) - Controls.getAxis(Controls.ElevatorDownAxis, 0.1)*Constants.kElevatorDownSpeed;

		boolean releaseHatchButton = Controls.getButton(Controls.ReleaseHatchButton);
		if(!ActionScheduler.getInstance().isActive() && releaseHatchButton && !bPressedLast) {
			ActionScheduler.getInstance().setGroup(new ReleaseHatch());
			ActionScheduler.getInstance().start();
		} else {
			Robot.elevatorBase.elevator(speed);
		}
		bPressedLast = releaseHatchButton;

		boolean pickupHatchButton = Controls.getButton(Controls.PickupHatchButton);
		if(!ActionScheduler.getInstance().isActive() && pickupHatchButton && !xPressedLast) {
			ActionScheduler.getInstance().setGroup(new PickupHatch());
			ActionScheduler.getInstance().start();
		} else {
			Robot.elevatorBase.elevator(speed);
		}
		xPressedLast = pickupHatchButton;
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
