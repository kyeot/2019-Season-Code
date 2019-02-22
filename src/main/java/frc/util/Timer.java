package frc.util;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Timer {

	double startTime;
	double endTime;
	double time;
	
	public Timer(double time) {
		this.time = time;
		startTime = 0;
		endTime = 0;
	}
	
	public void start(){
		startTime = RobotController.getFPGATime();
		endTime = startTime + (1000000 * time);
	}
	
	public boolean ring() {
		return RobotController.getFPGATime() >= endTime;
	}
	
}