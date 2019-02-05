package frc.robot.subsystems;

import frc.robot.commands.LinearActuator;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @purpose: Controlling the Linear Actuator Subsystem
 * @author Liam Shearin
 * @version 1/28/2019
 */
public class LinearActuatorBase extends Subsystem {
	
	//Creates Victor object
	VictorSPX front;
	VictorSPX back;
	VictorSPX driveMot;

	public LinearActuatorBase(){
		front = new VictorSPX(Constants.kFrontLinearActuatorId);
		back = new VictorSPX(Constants.kBackLinearActuatorId);
		driveMot = new VictorSPX(Constants.kDriveLinearActuatorId);
	
		front.setNeutralMode(NeutralMode.Brake);
		back.setNeutralMode(NeutralMode.Brake);
		driveMot.setNeutralMode(NeutralMode.Brake);
	}
	
	//Method to use Linear Actuator base
	public void linearActuator(double liftFrontSpeed, double liftBackSpeed, double driveSpeed) {
		front.set(ControlMode.PercentOutput, liftFrontSpeed);
		back.set(ControlMode.PercentOutput, liftBackSpeed);
		driveMot.set(ControlMode.PercentOutput, driveSpeed);
  	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LinearActuator());
	}

}


