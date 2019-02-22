package frc.robot.commands;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveController;
import frc.util.Bearing;
import frc.util.NavSensor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SwerveDrive extends Command {
	
	/*  
	 * Enum used to set the way in which swerve is controlled
	 * 
	 */
	public enum ControlType {
		CONTROLLER(1, 0, 4, 5, 9, 10, 4, 3, 6),
		JOYSTICK(1, 0, 2, 1, 7, 8, 2, 4, 6);
		
		int fbAxis;
		int rlAxis;
		int rotAxis;
		
		int halfSpeed;
		int sprint1;
		int sprint2;
		int centerGyro;
		int zeroModules;
		int dockingMode;
	
		/**
		 * Constructs the variables based on control type
		 * @param fbAxis Forward/Backward axis
		 * @param rlAxis Right/Left axis
		 * @param rotAxis Rotation axis
		 * @param halfSpeed Used to half speed if told
		 * @param sprint Doubles speed, Call of Duty mode
		 * @param centerGyro Used to reset the gyroscope
		 * @param zeroModules Zero's all the module's encoders
		 * @param dockingMode Used to set the robot to docking mode
		 */
		private ControlType(
				int fbAxis, int rlAxis, int rotAxis,
				int halfSpeed, int sprint1, int sprint2, int centerGyro, int zeroModules, int dockingMode) {
			
			this.fbAxis = fbAxis;
			this.rlAxis = rlAxis;
			this.rotAxis = rotAxis;
			this.halfSpeed = halfSpeed;
			this.sprint1 = sprint1;
			this.sprint2 = sprint2;
			this.centerGyro = centerGyro;
			this.zeroModules = zeroModules;
			this.dockingMode = dockingMode;
		}

		public double getFBAxis() {
			return OI.driver.getRawAxis(fbAxis);
		}
		
		public double getRLAxis() {
			return OI.driver.getRawAxis(rlAxis);
		}
		
		public double getRotAxis() {
			return OI.driver.getRawAxis(rotAxis);
		}
		
		public boolean getHalfSpeedButton() {
			return OI.driver.getRawButton(halfSpeed);
		}

		public boolean getSprint() {
			return OI.driver.getRawButton(sprint1) || OI.driver.getRawButton(sprint2);
		}
		
		public boolean getCenterGyroButton() {
			return OI.driver.getRawButton(centerGyro);
		}
		
		public boolean getZeroModulesButton() {
			return OI.driver.getRawButton(zeroModules);
		}
		
		public boolean getDockingModeButton() {
			return OI.driver.getRawButton(dockingMode);
		}
	}

	private ControlType controlType;
	private SwerveController swerveController = SwerveController.getInstance();

	boolean sprinting;

	//Makes SwerveDrive require the subsystem swerveBase
    public SwerveDrive(ControlType controlType) {
    	requires(Robot.swerveBase);
		this.controlType = controlType;
		sprinting = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Sets input for swerveDrive method as input from controller stick axes. Note: FBValue is negative as required by doc linked to in swerveDrive method
    	Double fbValue = controlType.getFBAxis()/2;
    	Double rlValue = -(controlType.getRLAxis())/2;
		Double rotValue = -controlType.getRotAxis()/2;
    	
    	//Makes it so if the left stick is barely moved at all it doesn't move at all
    	if ((fbValue > -.2 && fbValue < .2) && (rlValue > -.2 && rlValue < .2)){
    		fbValue = 0.0;
    		rlValue = 0.0;
    	}
    	
    	//Makes it so if the right stick is barely moved at all it doesn't move at all
    	if (rotValue > -.2 && rotValue < .2){
    		rotValue = 0.0;
    	}
    	
    	//While the left bumper is held go full speed
    	if(controlType.getHalfSpeedButton()) {
    		fbValue *= 0.7;
    		rlValue *= 0.7;
    		rotValue *= 0.7;
		}

		if(controlType.getSprint() && ((fbValue != 0) || (rlValue != 0) || (rotValue != 0))) {
			sprinting = true;
		} 
		if((fbValue == 0) && (rlValue == 0) && (rotValue == 0)) {
			sprinting = false;
		}
		
		if(sprinting) {
			fbValue *= 2;
			rlValue *= 2;
			rotValue *= 2;
		}

		if(sprinting) {
			SmartDashboard.putString("DB/String 9", "sprinting");
		} else {
			SmartDashboard.putString("DB/String 9", "not sprinting");
		}
		
		fbValue = 0.0;
		rlValue = 0.0;
		rotValue = 0.0;
    	
    	//If the X button is pressed resets the Swerve Modules
    	if(controlType.getZeroModulesButton()) {
			Robot.swerveBase.setZero();
    	}
    	
		//If Y is pressed resets the field orientation
    	if(controlType.getCenterGyroButton()) {
    		NavSensor.getInstance().resetGyroNorth(180, 0);
		}
		
		if(OI.driver.getPOV() != -1){
			swerveController.setPose(new Bearing(OI.driver.getPOV()));
		}
		else{
			swerveController.slide(fbValue, rlValue);
			swerveController.rotate(-rotValue);
		}
		//goes into docking mode
    	if(controlType.getDockingModeButton()) {
			System.out.println("Docking Mode");
			fbValue = -fbValue;
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
