package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;
import frc.autonomous.ActionScheduler;
import frc.autonomous.actiongroups.*;

/**
 * @purpose Command class for Elevator
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class Elevator extends Command {

    public static double speed;


    public Elevator() {
    	//Sets the required Subsystem
        requires(Robot.elevatorBase);

        speed = 0;
  
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
        
        speed = 0;

        
    	if(OI.manipulator.getRawButton(Constants.kElevatorUp)) {
            speed = 1;
       
        }
        else if(OI.manipulator.getRawButton(Constants.kElevatorDown)) {
           speed = -1;
          
            
        }
            
       if (OI.manipulator.getRawButton(Constants.kAutoLinearActuatorButtonId)) {
            ActionScheduler.getInstance().setGroup(new LinearActuatorGroup());
            ActionScheduler.getInstance().start();
        }
        else{
            if(!ActionScheduler.getInstance().isActive()){
                Robot.elevatorBase.elevator(speed);
            }
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
