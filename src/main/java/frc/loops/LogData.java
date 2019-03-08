package frc.loops;

import edu.wpi.first.wpilibj.RobotController;
import frc.util.EventLogger;

public class LogData implements Loop{
	
	EventLogger batteryHandler = new EventLogger("Battery Browned Out!", "WARN"){
		@Override
		public boolean event() {
			return RobotController.isBrownedOut();
		}
	};

	@Override
	public void onStart() {
		
	}

	@Override
	public void onLoop() {
		batteryHandler.handleEvent();
	}

	@Override
	public void onStop() {
		
	}

    @Override
    public void onLoop(double timestamp) {

    }

}
