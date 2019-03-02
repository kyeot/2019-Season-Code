/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.autonomous.ActionGroup;
import frc.autonomous.ActionScheduler;
import frc.autonomous.actiongroups.TestGroup;
import frc.loops.EncoderCounter;
import frc.loops.Looper;
import frc.loops.VisionProcessor;
import frc.robot.subsystems.ElevatorBase;
import frc.robot.subsystems.IntakeBase;
import frc.robot.subsystems.LinearActuatorBase;
import frc.robot.subsystems.SwerveDriveBase;
import frc.util.ElevatorEncoder;
import frc.util.Logger;
import frc.util.NavSensor;

public class Robot extends TimedRobot {
	NetworkTableInstance ntinst = NetworkTableInstance.getDefault();

	public static OI oi;

	public static SwerveDriveBase swerveBase = new SwerveDriveBase();
	public static LinearActuatorBase linearActuatorBase = new LinearActuatorBase();
	public static ElevatorBase elevatorBase = new ElevatorBase();
	public static IntakeBase intakeBase = new IntakeBase();

	public Looper looper = new Looper(Constants.kLoopPeriod);
	private static ActionScheduler actionScheduler = ActionScheduler.getInstance();
	public VisionProcessor visionProcessor = VisionProcessor.getInstance();
	public EncoderCounter encoderCounter = EncoderCounter.getInstance();

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		oi = new OI();

		looper.addLoop(visionProcessor);
		looper.addLoop(encoderCounter);
		looper.startLoops();

		String[] autonomousList = { "Test" };

		SmartDashboard.putStringArray("Auto List", autonomousList);

		File logFile = new File("/home/lvuser/log.txt");
		try {
			logFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		NavSensor.getInstance().resetGyroNorth(Constants.kRobotFront, 0);
	}

	public static void setGroup(ActionGroup group) {
		actionScheduler.setGroup(group);
	}

	@Override
	public void robotPeriodic() {

		/*
		 * Prints for the Absolute Encoder Angles, Use to calculate offsets
		 * SmartDashboard.putString("DB/String 0", "fl: " +
		 * swerveBase.flMod.getAngle()); SmartDashboard.putString("DB/String 1", "fr: "
		 * + swerveBase.frMod.getAngle()); SmartDashboard.putString("DB/String 2",
		 * "rl: " + swerveBase.rlMod.getAngle());
		 * SmartDashboard.putString("DB/String 3", "rr: " +
		 * swerveBase.rrMod.getAngle());
		 */

		SmartDashboard.putString("DB/String 1", "" + NavSensor.getInstance().navSensor.isConnected());

	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Logger.info("Starting Autonomous");

		String autoSelected = SmartDashboard.getString("Auto Selector", "None");

		switch (autoSelected) {
		case "Test":
			setGroup(new TestGroup());
			break;
		}

		actionScheduler.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}

		EncoderCounter.zero();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}

	public static String parseMatchTime() {
		double s = DriverStation.getInstance().getMatchTime();

		if (s != -1.0) {
			if (DriverStation.getInstance().isAutonomous()) {

				int t = (int) (15 - Math.ceil(s));
				return ":" + Integer.toString((int) t) + " (Auton)";

			} else if (DriverStation.getInstance().isOperatorControl()) {

				int t = (int) (135 - Math.ceil(s));
				return Integer.toString((int) Math.floor(t / 60)) + ":" + Integer.toString((int) t % 60) + " (TeleOp)";

			} else {

				return "Disabled";

			}

		} else {

			return "Not Practice";

		}

	}

}
