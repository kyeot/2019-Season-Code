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
public class AutoLaHold extends Action {
	
	
	double speed;
	

	public AutoLaHold( double speed, double time) {
		super("AutoLaHold", time);
		
	
		this.speed = speed;
		
	}
	
	@Override
	public void perform() {
		Robot.linearActuatorBase.linearActuator( speed );
	}
	
}

 