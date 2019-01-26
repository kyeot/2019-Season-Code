/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import frc.robot.subsystems.SwerveDriveBase;
import frc.robot.subsystems.ManipulatorBase;
import frc.robot.subsystems.SwerveController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

import frc.loops.Looper;
import frc.util.Logger;
import frc.util.NavSensor;
import frc.autonomous.*;
import frc.autonomous.actiongroups.*;

import com.kauailabs.navx.frc.AHRS;

public class Robot extends TimedRobot {
  public static OI oi;

  public static SwerveDriveBase swerveBase = new SwerveDriveBase();
  public static ManipulatorBase manipBase = new ManipulatorBase();

  private static AHRS navSensor;

  public Looper looper = new Looper(Constants.kPeriod);
  public static ActionScheduler actionScheduler = new ActionScheduler();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    oi = new OI();

    looper.startLoops();

    String[] autonomousList = {"Test"};
    
		SmartDashboard.putStringArray("Auto List", autonomousList);
    
    try {
       navSensor = new AHRS(SPI.Port.kMXP);
   } catch (RuntimeException ex ) {
       DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
   }
   Robot.swerveBase.resetGyroNorth(180, 0);
  }
  
  
	public static void setGroup(ActionGroup group) {
		actionScheduler.setGroup(group);
	}

  @Override
  public void robotPeriodic() {
    SmartDashboard.putString("DB/String 5", "" + NavSensor.getInstance().getAngle(false));
    SmartDashboard.putString("DB/String 6", "" + OI.driver.getPOV());
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
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void testPeriodic() {
  }

  public static AHRS getNavSensor() {
		return navSensor;
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
