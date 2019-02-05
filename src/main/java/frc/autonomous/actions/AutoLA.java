package frc.autonomous.actions;

import frc.robot.Robot;
import frc.autonomous.Action;
/**
 * 
 * Give it a PercentOutPut on both sides and give it a time to run
 * 
 * @author 2783 Liam Shearin
 *
 */
public class AutoLA extends Action {
	
	double liftFrontSpeed;
	double liftBackSpeed;
	double driveSpeed;

	public AutoLA(double liftFrontSpeed, double liftBackSpeed, double driveSpeed, double time) {
		super("AutoLA", time);
		
		this.liftFrontSpeed = liftFrontSpeed;
		this.liftBackSpeed = liftBackSpeed;
		this.driveSpeed = driveSpeed;
	}
	
	@Override
	public void perform() {
		Robot.linearActuatorBase.linearActuator(liftFrontSpeed, liftBackSpeed, driveSpeed);
	}
	
}

 