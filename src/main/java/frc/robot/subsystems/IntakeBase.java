package frc.robot.subsystems;

import frc.robot.commands.Elevator;
import frc.robot.commands.Intake;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @purpose: Controlling the Intake Subsystem
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class IntakeBase extends Subsystem {
	
	//Creates Victor object
	VictorSPX intake;
	Servo testServo;


	public IntakeBase(){
		intake = new VictorSPX(Constants.kIntakeId);
		testServo = new Servo(1);

		intake.setNeutralMode(NeutralMode.Brake);
	}

	public void testServo(double angle) {
		testServo.setAngle(angle);
	}

	public double getServo(){
		return testServo.getAngle();
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


