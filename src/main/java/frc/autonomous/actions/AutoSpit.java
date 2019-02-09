package frc.autonomous.actions;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.autonomous.Action;
/**
 * 
 * Give it a PercentOutPut on both sides and give it a time to run
 * 
 * @author 2783 Liam Shearin
 *
 */
public class AutoSpit extends Action {
	
	double speed;


	public AutoSpit(double speed, double time) {
		super("AutoSpit", time);
		
		this.speed = speed;
		
	}
	
	@Override
	public void perform() {
        Robot.intakeBase.intake(speed);
	}
	@Override 
	public void finish(){
		Robot.intakeBase.intake(0);
	}
}

 