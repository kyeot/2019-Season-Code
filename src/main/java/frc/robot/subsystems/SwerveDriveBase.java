package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.SwerveDrive;
import frc.util.Logger;
import frc.util.NavSensor;

/**
 * Subsystem used to control the Swerve Drive
 * 
 * 
 *
 */
public class SwerveDriveBase extends Subsystem {
	
	public SwerveModule frMod;
	public SwerveModule flMod;
	public SwerveModule rrMod;
	public SwerveModule rlMod;
	
	/**
	 * 
	 * Class used to set the swivel motor to the value calculated by the PID controller
	 *
	 */
	public class PIDOutputClass implements PIDOutput {
		private VictorSPX motor;
		
		public PIDOutputClass(VictorSPX swivelMot) {
			this.motor = swivelMot;
		}
		
		@Override
		public void pidWrite(double output) {
			motor.set(ControlMode.PercentOutput, output);
		}
	}

	public class PIDAnalogInput implements PIDSource {
		PIDSourceType sourceType;

		AnalogInput enc;
		double angleOffset;
		double rotRatio;

		double angle;

		public PIDAnalogInput(AnalogInput enc, double angleOffset, double rotRatio) {
			setPIDSourceType(PIDSourceType.kDisplacement);

			this.enc = enc;
			this.angleOffset = angleOffset;
			this.rotRatio = rotRatio;
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
			angle = ((enc.getValue()*rotRatio)/Constants.kAnalogInputToDegreeRatio)+angleOffset;

			angle %= 360;

			return angle;
		}
	}
	
	/**
	 * 
	 * Class used to make a swerve module
	 *
	 */
	public class SwerveModule {
		public VictorSPX driveMot;
		public VictorSPX swivelMot;
		PIDAnalogInput enc;
		
		PIDOutputClass pidOut;
		PIDController pidCont;
		
		double lastAngle;
		
		/**
		 * Constructs the swerve module
		 * @param swivelMot The motor that controls the module's rotation
		 * @param driveMot The motor that controls the module's drive speed
		 * @param enc Encoder that tracks the angle of the swivel motor
		 */
		public SwerveModule(
				VictorSPX swivelMot,
				VictorSPX driveMot,
				PIDAnalogInput enc
				) {
			
			this.driveMot = driveMot;
			this.swivelMot = swivelMot;
			this.enc = enc;
			
			pidOut = new PIDOutputClass(
							swivelMot
						);
			
			pidCont = new PIDController(
							Constants.kSwerveP, Constants.kSwerveI, Constants.kSwerveD,
							enc,
							pidOut
						);
			
			
			driveMot.setNeutralMode(NeutralMode.Coast);
			swivelMot.setNeutralMode(NeutralMode.Brake);
			driveMot.configOpenloopRamp(Constants.kSwerveRampRate);
			pidCont.setInputRange(0, 360);
			pidCont.setContinuous();
		}
		
		/**
		 * 
		 * Method that sets a module's angle of the module and the speed of the drive wheel
		 * @param angle Angle of the module
		 * @param speed Speed of the drive wheel
		 */
		public void setModule(double angle, double speed) {
			
			//Keeps the angle within 0 and 360
			if(angle < 0) {
				angle += 360;
			}
			
			//Resets the encoder if it gets too high or low
			double curAngle = getAngle();
			//if(curAngle >= 360 || curAngle <= -360) {
			//	enc.reset();
			//}
			
			//Makes the module go to the opposite angle and go backwards when it's quicker than turning all the way around
	    	if(Math.abs(angle - curAngle) > 90 && Math.abs(angle - curAngle) < 270 && angle != 0) {
	    		angle = (angle +180)%360;
	    		speed = -speed;
	    	}

	    	//Makes it so when you stop moving it doesn't reset the angle to 0, but leaves it where it was
		    if(speed == 0) {
	    		setAngle(lastAngle);
		    	setSpeed(speed);
	    	} else {
	    		setAngle(angle);
	    		setSpeed(speed);
	    		
	    		lastAngle = angle;
			}
		}
		
		//Sets the module to a specific angle
		public void setAngle(double angle) {
			pidCont.enable();
			pidCont.setSetpoint(angle);
		}
		
		//Sets the drive motor's speed
		public void setSpeed(double speed) {
			driveMot.set(ControlMode.PercentOutput, speed);
		}
		
		//Sets the Swivel Motor's speed
		public void setSwivel(double speed) {
			swivelMot.set(ControlMode.PercentOutput, speed);
		}

		//Returns where the Encoder is
		//public double getEncPercent() {
		//	return enc.getDistance();
		//}
		
		//Gets the current angle of the module
		public double getAngle() {
			if (enc != null) {
				return enc.pidGet();
			} else {
				return -1.0;
			}
		}
		
		//Sets a motor to brake mode
		public void setBrake(boolean bool) {
			if(bool){
				driveMot.setNeutralMode(NeutralMode.Brake);
			}
			else{
				driveMot.setNeutralMode(NeutralMode.Coast);
			}
		}
	}
	
	//Returns the cosine of an angle in radians
	public double cosDeg(double deg) {
		return Math.cos(Math.toRadians(deg));
	}
	
	//Returns the sine of an angle in radians
	public double sinDeg(double deg) {
		return Math.sin(Math.toRadians(deg));
	}
	
	/**
	 * Constructs the swerve modules, each with a VictorSP, a CANTalon, and an Encoder
	 */
    public SwerveDriveBase() {
    	super();
    	
    	//Creates the front right Swerve Module
    	flMod = new SwerveModule(
    					new VictorSPX(Constants.kFrontLeftSwivelId),
    					new VictorSPX(Constants.kFrontLeftWheelId),
						new PIDAnalogInput(new AnalogInput(Constants.kFrontLeftAbsoluteEncoder), 
						360 - Constants.kFrontLeftAngleOffset, 1)
    				);
    	
    	//Creates the front left Swerve Module
    	rlMod = new SwerveModule(
    					new VictorSPX(Constants.kRearLeftSwivelId),
    					new VictorSPX(Constants.kRearLeftWheelId),
						new PIDAnalogInput(new AnalogInput(Constants.kRearLeftAbsoluteEncoder), 
						360 - Constants.kRearLeftAngleOffset, 1)
    				);
    	
    	//Creates the rear right Swerve Module
    	frMod = new SwerveModule(
    					new VictorSPX(Constants.kFrontRightSwivelId),
    					new VictorSPX(Constants.kFrontRightWheelId),
						new PIDAnalogInput(new AnalogInput(Constants.kFrontRightAbsoluteEncoder),
						360 - Constants.kFrontRightAngleOffset, 1)
    				);
    			
    	//Creates the rear left Swerve Module
    	rrMod = new SwerveModule(
    					new VictorSPX(Constants.kRearRightSwivelId),
    					new VictorSPX(Constants.kRearRightWheelId),
						new PIDAnalogInput(new AnalogInput(Constants.kRearRightAbsoluteEncoder), 
						360 - Constants.kRearRightAngleOffset, 1)
					); // ):
    }

    //Initiates SwerveDrive as the Default Command
    public void initDefaultCommand() {
        setDefaultCommand(new SwerveDrive());
    }
    
    /**
     * A simple tank drive that uses the swerve modules with a left value and a right value
     * @param leftValue Speed of the left side
     * @param rightValue Speed of the right side
     */
    public void tankDrive(double leftValue, double rightValue) {
    	if (DriverStation.getInstance().isFMSAttached() && DriverStation.getInstance().getMatchTime() < 4) {
    		setRobotBrake(true);
    	} else {
    		setRobotBrake(true);
    	}
    	
    	frMod.setSpeed(rightValue);
    	rrMod.setSpeed(rightValue);
    	
    	flMod.setSpeed(leftValue);
    	rlMod.setSpeed(leftValue);
    }
    
    /**
     * Method for calculating and setting Speed and Angle of individual wheels given 3 movement inputs
     * 
     * Swerve Math Taken from: https://www.chiefdelphi.com/media/papers/2426
     * 
     * @param fbMot Forward/Backward Motion
     * @param rlMot Right/Left Motion
     * @param rotMot Rotation Motion
     * @param fieldOriented If it's field oriented or not
     */
    public void swerveDrive(double fbMot, double rlMot, double rotMot, boolean fieldOriented) {
    	//Swerve Math Taken from: https://www.chiefdelphi.com/media/papers/2426
    	
    	if(fieldOriented) {
	    	double curAngle = NavSensor.getInstance().getAngle(false);
	    	double temp = fbMot*(cosDeg(curAngle)) + rlMot*(sinDeg(curAngle));
	    	rlMot = fbMot*(sinDeg(curAngle)) + -(rlMot*(cosDeg(curAngle)));
	    	fbMot = temp;
    	}
    	
    	double L = 19.5;
    	double W = 23;
    	double R = Math.sqrt((L*L) + (W*W));
    	
    	double A = rlMot + rotMot*(L/R);
    	double B = rlMot - rotMot*(L/R);
    	double C = fbMot + rotMot*(W/R);
    	double D = fbMot - rotMot*(W/R);
    	
    	double frSpd = Math.sqrt((B*B) + (D*D));
    	double flSpd = Math.sqrt((B*B) + (C*C));
    	double rlSpd = Math.sqrt((A*A) + (C*C));
    	double rrSpd = Math.sqrt((A*A) + (D*D));
    	
    	double t = 180/Math.PI;
    	
    	double frAng = Math.atan2(B, D)*t;
    	double flAng = Math.atan2(B, C)*t;
    	double rlAng = Math.atan2(A, C)*t;
    	double rrAng = Math.atan2(A, D)*t;
    	 
    	double max = frSpd;
    	if(max < flSpd) max = flSpd;
    	if(max < rlSpd) max = rlSpd;
    	if(max < rrSpd) max = rrSpd;
    	//I'm so sorry Jake

    	//Father Jacobs forgives you
    	
    	if(max > 1) {
    		frSpd /= max;
    		flSpd /= max;
    		rlSpd /= max;
    		rrSpd /= max;
    	}
    	
    	//Set Wheel Speeds and Angles
    	frMod.setModule(frAng, frSpd);
    	flMod.setModule(flAng, flSpd);
    	rrMod.setModule(rrAng, rrSpd);
		rlMod.setModule(rlAng, rlSpd);

    }
    
    /**
     * A different way of controlling the swerve drive that drives in a direction you give it with a speed that you give it
     * @param angle The angle at which the robot drives
     * @param speed The speed at which the robot drives
     * @param rotation How fast the robot rotates
     * @param fieldOriented Whether or not the robot is field oriented
     */
	
    public void polarSwerveDrive(double angle, double speed, double rotation, boolean fieldOriented) {
    	swerveDrive(
    			cosDeg(angle)*speed,
    			sinDeg(angle)*speed,
    			rotation,
    			fieldOriented);
    }
    
    //Sets all module's angles to 0
    public void setZero() {
    	frMod.lastAngle = 0;
    	flMod.lastAngle = 0;
    	rrMod.lastAngle = 0;
    	rlMod.lastAngle = 0;
    	
    	frMod.setAngle(0);
    	flMod.setAngle(0);
    	rrMod.setAngle(0);
    	rlMod.setAngle(0);
    }
    
    //Sets every module on the robot to brake
    public void setRobotBrake(boolean bool) {
    	frMod.setBrake(bool);
    	flMod.setBrake(bool);
    	rrMod.setBrake(bool);
    	rlMod.setBrake(bool);
    }
    
}