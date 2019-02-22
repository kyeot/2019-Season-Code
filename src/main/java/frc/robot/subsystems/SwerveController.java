package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.util.Bearing;
import frc.util.NavSensor;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Singleton class which can control swerve from anywhere in the code.
 * 
 * @author 2783
 */
public class SwerveController {
	static SwerveController swerveCont = new SwerveController();
	
	public static SwerveController getInstance(){
		return swerveCont;
	}
	
	//Pid Output classs that writes to the local rotation value
	class SwervePoseOut implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			rot = -output;
		}
	}
	
	//Pid Source class that gets the gyro angle
	class GyroSource implements PIDSource {
		PIDSourceType sourceType;
		
		public GyroSource() {
			setPIDSourceType(PIDSourceType.kDisplacement);
		}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			sourceType = pidSource;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return sourceType;
		}

		@Override
		public double pidGet() {
			return gyro.getAngle(false);
		}
	}
	
	NavSensor gyro = NavSensor.getInstance();
	
	double fb;
	double rl;
	double rot;
	
	PIDController posePid;
	SwervePoseOut posePidOut;
	GyroSource posePidSource;
	
	SwerveController(){
		posePidOut = new SwervePoseOut();
		posePidSource = new GyroSource();
		posePid = new PIDController(Constants.kSwerveRotP, Constants.kSwerveRotI, Constants.kSwerveRotD, 
										posePidSource, 
										posePidOut);
		posePid.setInputRange(0, 360);
		posePid.setContinuous();
	}
	
	//Sets fb and rl motion value
	public void slide(double fb, double rl) {
		this.fb = fb;
		this.rl = rl;
	}
	
	//Sets rotation value
	public void rotate(double rot) {
		posePid.disable();
		this.rot = rot;
	}
	
	//rotate() + slide()
	public void move(double fb, double rl, double rot) {
		slide(fb, rl);
		rotate(rot);
	}
	
	//Uses Pid to set Robot to angle
	public void setPose(Bearing b) {
		posePid.setSetpoint(b.getTheta());
		posePid.enable();
	}
	
	//Returns if the Pid error is within a certain range
	public boolean poseWithinRange(double range) {
		return Math.abs(posePid.getError()) < range;
	}
	
	//Must be called periodically to control swerve
	public void update(boolean fieldOriented) {
		Robot.swerveBase.swerveDrive(fb, rl, rot, fieldOriented);
	}
}