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
public class AutoLaUP extends Action {
	
	
	double speed;
	

	public AutoLaUP( double speed, double time) {
		super("AutoLaUP", time);
		
	
		this.speed = speed;
		
	}
	
	@Override
	public void perform() {
		Robot.bothLa.linearActuator( speed );
	}
	
}

 