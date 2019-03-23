package frc.loops;

import edu.wpi.first.wpilibj.RobotController;
import frc.robot.Robot;
import frc.util.EventLogger;
import frc.util.Logger;
import java.util.Date;
import java.io.File;
import java.io.IOException;

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
		//batteryHandler.handleEvent();
		
		Logger.info("CONTROLLER" + " - " + "Voltage: " + RobotController.getBatteryVoltage() + " " + "BrownedOut: " + RobotController.isBrownedOut());
		
		Logger.logTalon(Robot.elevatorBase.elevator, "Elevator");	
		Logger.logTalon(Robot.intakeBase.intake, "Intake");
		
		Logger.logTalon(Robot.linearActuatorBase.front, "Front LA");
		Logger.logTalon(Robot.linearActuatorBase.back, "Back LA");
		Logger.logTalon(Robot.linearActuatorBase.drive, "LA Drive");
		
		Logger.logVictor(Robot.swerveBase.frMod.driveMot, "Front Right Drive");
		Logger.logVictor(Robot.swerveBase.flMod.driveMot, "Front Left Drive");
		Logger.logVictor(Robot.swerveBase.rrMod.driveMot, "Rear Right Drive");
		Logger.logVictor(Robot.swerveBase.rlMod.driveMot, "Rear Left Drive");

		Logger.logVictor(Robot.swerveBase.frMod.swivelMot, "Front Right Swivel");
		Logger.logVictor(Robot.swerveBase.flMod.swivelMot, "Front Left Swivel");
		Logger.logVictor(Robot.swerveBase.rrMod.swivelMot, "Rear Right Swivel");
		Logger.logVictor(Robot.swerveBase.rlMod.swivelMot, "Rear Left Swivel");

	}

	@Override
	public void onStop() {
		
	}

    @Override
    public void onLoop(double timestamp) {

    }

}
