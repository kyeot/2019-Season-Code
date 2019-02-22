package frc.autonomous.actions;

import frc.robot.Robot;
import frc.autonomous.Action;
/**
 * 
 * Give it a PercentOutPut on both sides and give it a time to run
 * 
 * @author 2783
 *
 */
public class AutoDrive extends Action {
	
	double angle;
	double speed;
	double rotation;
	
	//drives autonomously
	public AutoDrive(double angle, double speed, double rotation, double time) {
		super("AutoDrive", time);
		
		this.angle = angle;
		this.speed = speed;
		this.rotation = rotation;
	}
	
	@Override
	public void perform() {
		Robot.swerveBase.polarSwerveDrive(angle, speed, rotation, true);
	}
	
}