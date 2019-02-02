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
public class AutoLaDrive extends Action {
	
	
	double speed;
	

	public AutoLaDrive( double speed, double time) {
		super("AutoLaDrive", time);
		
	
		this.speed = speed;
		
	}
	
	@Override
	public void perform() {
		Robot.laDriveBase.laDrive( speed );
	}
	
}

 