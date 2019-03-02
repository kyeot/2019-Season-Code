package frc.robot.subsystems;

import frc.robot.commands.LinearActuator;
import frc.robot.*;
import frc.util.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @purpose: Controlling the Linear Actuator Subsystem
 * @author Liam Shearin
 * @version 1/28/2019
 */
public class LinearActuatorBase extends Subsystem {

	// Creates Victor object
	TalonSRX front;
	TalonSRX back;
	TalonSRX driveMot;

	DigitalInput backDigitalInputBot;
	DigitalInput backDigitalInputTop;
	DigitalInput frontDigitalInputBot;
	DigitalInput frontDigitalInputTop;

	Timer delay;

	// Makes linear actuators stop
	public LinearActuatorBase() {
		front = new TalonSRX(Constants.kFrontLinearActuatorId);
		back = new TalonSRX(Constants.kBackLinearActuatorId);
		driveMot = new TalonSRX(Constants.kLinearActuatorDriveMotId);

		backDigitalInputBot = new DigitalInput(0);
		backDigitalInputTop = new DigitalInput(1);
		frontDigitalInputTop = new DigitalInput(2);
		frontDigitalInputBot = new DigitalInput(3);

		front.setNeutralMode(NeutralMode.Brake);
		back.setNeutralMode(NeutralMode.Brake);
		driveMot.setNeutralMode(NeutralMode.Brake);
	}

	// Method to use Linear Actuator base
	public void linearActuator(double liftFrontSpeed, double liftBackSpeed, double driveSpeed) {
		//if (!frontDigitalInputBot.get() && liftFrontSpeed < -0.1) {
			front.set(ControlMode.PercentOutput, liftFrontSpeed * 0.5);
		//} else if (!frontDigitalInputTop.get() && liftFrontSpeed > 0.1) {
		//	front.set(ControlMode.PercentOutput, liftFrontSpeed * 0.5);
		//} else {
		//	front.set(ControlMode.PercentOutput, 0);
		//}

		/* Prints for the Linear Actuator Limit Switches
		SmartDashboard.putString("DB/String 5", "FRONT BOT: " + frontDigitalInputBot.get());
		SmartDashboard.putString("DB/String 6", "FRONT TOP: " + frontDigitalInputTop.get());
		SmartDashboard.putString("DB/String 7", "BACK BOT: " + backDigitalInputBot.get());
		SmartDashboard.putString("DB/String 8", "BACK TOP: " + backDigitalInputTop.get());
		*/

		if (!backDigitalInputBot.get() && liftBackSpeed < -0.1) {
			back.set(ControlMode.PercentOutput, liftBackSpeed * 0.5);
		} else if (!backDigitalInputTop.get() && liftBackSpeed > 0.1) {
			back.set(ControlMode.PercentOutput, liftBackSpeed * 0.5);
		}
		else{
			back.set(ControlMode.PercentOutput, 0);
		}

		driveMot.set(ControlMode.PercentOutput, driveSpeed);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LinearActuator());
	}

}
