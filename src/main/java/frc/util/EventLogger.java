package frc.util;

import edu.wpi.first.wpilibj.RobotController;
import frc.robot.Constants;

public abstract class EventLogger {
	
	String msg;
	String lvl;
	double timeLast = 0;
	
	public EventLogger(String msg, String loggerLevel) {
		this.msg = msg;
		this.lvl = loggerLevel;
	}

	public abstract boolean event();
	
	public void handleEvent() {
		if(event() && ((RobotController.getFPGATime()-timeLast) > Constants.kEventDelay*1000000)) {
			Logger.log(lvl, msg);
			timeLast = RobotController.getFPGATime();
		}
	}
	
}