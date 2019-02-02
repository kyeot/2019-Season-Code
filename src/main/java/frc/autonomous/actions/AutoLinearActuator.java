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
public class AutoLinearActuator extends Action {
	
	double angle;
	double speed;
	double rotation;

	public AutoLinearActuator( double speed, double time) {
		super("AutoLinearActuator", time);
		
	
		this.speed = speed;
		
	}
	
	@Override
	public void perform() {
		Robot.linearActuatorBase.linearActuator( speed );
	}
	
}

 