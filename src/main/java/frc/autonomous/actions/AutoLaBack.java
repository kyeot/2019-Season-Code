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
public class AutoLaBack extends Action {
	
	
	double speed;
	

	public AutoLaBack( double speed, double time) {
		super("AutoLaBack", time);
		
	
		this.speed = speed;
		
	}
	
	@Override
	public void perform() {
		Robot.bothLa.linearActuator( speed );
	}
	
}

 