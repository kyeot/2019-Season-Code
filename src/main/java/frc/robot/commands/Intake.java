package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;
import frc.autonomous.ActionScheduler;
import frc.autonomous.actiongroups.*;

/**
 * @purpose Command class for Intake
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class Intake extends Command {

    public static double speed;


    public Intake() {
    	//Sets the required Subsystem
        requires(Robot.intakeBase);

        speed = 0;
  
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
     // Trigger and Joystick Controls

      speed = 0;
    	
      if(OI.manipulator.getRawButton(Constants.kIntakeIn)){
    	  speed = -1;
      }
    	
      else if(OI.manipulator.getRawButton(Constants.kIntakeOut)){
    	  speed = 1;
      }
      
      else if(OI.manipulator.getRawAxis(5) > 0.3 || OI.manipulator.getRawAxis(5) < -0.3){
        speed = -OI.manipulator.getRawAxis(5);
      }

      else if(OI.manipulator.getRawButton(3)){
        ActionScheduler.getInstance().setGroup(new ServoGroup());
        ActionScheduler.getInstance().start();
      }

      //Button Controls
      /*      speed = 0;
   
    	if(OI.manipulator.getRawButton(Constants.kIntakeIn)) {
            speed = -1;
       
        }
        else if(OI.manipulator.getRawButton(Constants.kIntakeOut)) {
           speed = 1;
          
            
        }
  */          
        if(!ActionScheduler.getInstance().isActive()){
              Robot.intakeBase.intake(speed);
        }
              
    }

    // Make this return true when this Command no
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
