/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import edu.wpi.first.*;

public class LinearActuator extends Command {
  private static final boolean BOOLEAN = true;

  /**
   * @purpose Command class for Linear Actuators
   * @author Liam Shearin
   * @version 1/26/2019
   */
	
  public LinearActuator() {
    //Sets the required Subsystem
      requires(Robot.LinearActuator);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute()



  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
      return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
