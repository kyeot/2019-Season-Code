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
		
		frontLiftSpeed = Controls.getAxis(Controls.LA_OUT_DPAD) - Controls.getAxis(Controls.LA_IN_DPAD);
			
		/*if(OI.manipulator.getPOV() == 0){
			frontLiftSpeed = -1;
		}
		else if(OI.manipulator.getPOV() == 180){
			frontLiftSpeed = 1;
		}
		else{
			frontLiftSpeed = 0;
		}*/
		
		if(OI.manipulator.getRawButton(1)){
			backLiftSpeed = -1;
		}
		else if(OI.manipulator.getRawButton(4)){
			backLiftSpeed = 1;
		}
		else{
			backLiftSpeed = 0;
		}

		if(Controls.getButton(Controls.LA_DRIVE_FORWARD_BUTTON)){
			driveSpeed = 1;
		}
		else if(Controls.getButton(Controls.LA_DRIVE_BACKWARD_BUTTON)){
			driveSpeed = -1;
		}
		else{
			driveSpeed = 0;
		}

		//climbs when button is pressed
		if (Controls.getButton(Controls.LA_AUTO_BUTTON)) {
			ActionScheduler.getInstance().setGroup(new LinearActuatorGroup());
			ActionScheduler.getInstance().start();
		}
		else if(!ActionScheduler.getInstance().isActive()){
				Robot.linearActuatorBase.linearActuator(frontLiftSpeed, backLiftSpeed, driveSpeed);
		}
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
