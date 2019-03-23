package frc.robot.subsystems;

import frc.robot.commands.Intake;
import frc.util.Logger;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @purpose: Controlling the Intake Subsystem
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class IntakeBase extends Subsystem {
	
	//Creates Victor object
	public static  TalonSRX intake;
	Servo latchServo;

	boolean latched;

	public IntakeBase(){
		intake = new TalonSRX(Constants.kIntakeId);
		latchServo = new Servo(Constants.kServoId);

		intake.setNeutralMode(NeutralMode.Brake);

		latchServo.setAngle(Constants.kServoLatchedAngle);
		latched = true;
	}

	public void toggleLatch() {
		if(latched) {
			latchServo.setAngle(Constants.kServoUnlatchedAngle);
		} else {
			latchServo.setAngle(Constants.kServoLatchedAngle);
		}
		latched = !latched;
	}

	public double getServo(){
		return latchServo.getAngle();
	}

	//Method to use Intake base
	public void intake(double speed) {
		intake.set(ControlMode.PercentOutput, speed);
  	}

  @Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Intake());
	}

}


