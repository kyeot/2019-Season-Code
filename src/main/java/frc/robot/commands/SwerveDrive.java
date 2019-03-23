package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Controls;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveController;
import frc.util.Bearing;
import frc.util.NavSensor;

/**
 *
 */
public class SwerveDrive extends Command {

	private SwerveController swerveController = SwerveController.getInstance();

	boolean sprinting;

	//Makes SwerveDrive require the subsystem swerveBase
    public SwerveDrive() {
    	requires(Robot.swerveBase);
		sprinting = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Sets input for swerveDrive method as input from controller stick axes. Note: FBValue is negative as required by doc linked to in swerveDrive method
    	Double fbValue = Controls.getAxis(Controls.FbAxis, 0.2)*Constants.normalSpeedSpeed;
    	Double rlValue = -Controls.getAxis(Controls.RlAxis, 0.2)*Constants.normalSpeedSpeed;
		Double rotValue = -Controls.getAxis(Controls.RotAxis, 0.2)*Constants.normalSpeedSpeed;
    	
    	//While the left bumper is held go full speed
    	if(Controls.getButton(Controls.HalfSpeedButton) && !Controls.getButton(Controls.SlowVariableAxis, 0.1)) {
    		fbValue *= Constants.halfSpeedSpeed;
    		rlValue *= Constants.halfSpeedSpeed;
    		rotValue *= Constants.halfSpeedSpeed;
		} else if(Controls.getButton(Controls.SlowVariableAxis, 0.1)) {
			fbValue *= 1-Controls.getAxis(Controls.SlowVariableAxis);
    		rlValue *= 1-Controls.getAxis(Controls.SlowVariableAxis);
    		rotValue *= 1-Controls.getAxis(Controls.SlowVariableAxis);
		}

		if(Controls.getButton(Controls.SprintButton) && ((fbValue != 0) || (rlValue != 0) || (rotValue != 0))) {
			sprinting = true;
		} 
		if((fbValue == 0) && (rlValue == 0) && (rotValue == 0)) {
			sprinting = false;
		}
		
		if(sprinting && !Controls.getButton(Controls.FastVariableAxis, 0.1)) {
			fbValue *= Constants.fastSpeedSpeed;
			rlValue *= Constants.fastSpeedSpeed;
			rotValue *= Constants.fastSpeedSpeed;
		} else if(Controls.getButton(Controls.FastVariableAxis, 0.1)) {
			fbValue *= 1+Controls.getAxis(Controls.FastVariableAxis);
			rlValue *= 1+Constants.fastSpeedSpeed*Controls.getAxis(Controls.FastVariableAxis);
			rotValue *= 1+Constants.fastSpeedSpeed*Controls.getAxis(Controls.FastVariableAxis);
		}
    	
    	//If the X button is pressed resets the Swerve Modules
    	if(Controls.getButton(Controls.ZeroModulesButton)) {
			Robot.swerveBase.setZero();
    	}
		
		//If Y is pressed resets the field orientation
    	if(Controls.getButton(Controls.CenterGyroButton)) {
    		NavSensor.getInstance().resetGyroNorth(Constants.kGyroResetAngle, 0);
		}
		
		if(OI.driver.getPOV() != -1){
			swerveController.setPose(new Bearing(OI.driver.getPOV()));
		}
		else{
			if(Controls.getButton(Controls.DockingModeButton)) {
				fbValue = -fbValue;
			}
			swerveController.slide(fbValue, rlValue);
			swerveController.rotate(-rotValue);
		}
		//goes into docking mode
    	if(Controls.getButton(Controls.DockingModeButton)) {
			System.out.println("Docking Mode");
    		swerveController.update(false);
    	} else {
    		swerveController.update(true);
    	}
    }

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
        //i wish owen would die
    }

}
