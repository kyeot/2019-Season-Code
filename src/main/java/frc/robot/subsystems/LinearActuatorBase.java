package frc.robot.subsystems;

import frc.robot.commands.LinearActuator;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	DigitalInput backDigitalInputBot;
	DigitalInput backDigitalInputTop;
	DigitalInput frontDigitalInputBot;
	DigitalInput frontDigitalInputTop;

	//makes linear actuators stop
	public LinearActuatorBase(){
		front = new VictorSPX(Constants.kFrontLinearActuatorId);
		back = new VictorSPX(Constants.kBackLinearActuatorId);
		driveMot = new VictorSPX(Constants.kLinearActuatorDriveMotId);

		backDigitalInputBot = new DigitalInput(0);
		backDigitalInputTop = new DigitalInput(1);
		frontDigitalInputTop = new DigitalInput(2);
		frontDigitalInputBot = new DigitalInput(3);


		front.setNeutralMode(NeutralMode.Brake);
		back.setNeutralMode(NeutralMode.Brake);
		driveMot.setNeutralMode(NeutralMode.Brake);
	}
	
	//Method to use Linear Actuator base
	public void linearActuator(double liftFrontSpeed, double liftBackSpeed, double driveSpeed) {
		if (!frontDigitalInputBot.get() && liftFrontSpeed < -0.1) {
			front.set(ControlMode.PercentOutput, liftFrontSpeed*0.5);
		} 
		else if(!frontDigitalInputTop.get() && liftFrontSpeed > 0.1){
			front.set(ControlMode.PercentOutput, liftFrontSpeed*0.5);
		}
		else{
			front.set(ControlMode.PercentOutput, 0);
		}

		SmartDashboard.putString("DB/String 3", "BOT: " + frontDigitalInputBot.get());
		SmartDashboard.putString("DB/String 4", "TOP: " + frontDigitalInputTop.get());

		if (!backDigitalInputBot.get() && liftBackSpeed < -0.1) {
			back.set(ControlMode.PercentOutput, liftBackSpeed*0.5);
		} 
		else if(!backDigitalInputTop.get() && liftBackSpeed > 0.1){
			back.set(ControlMode.PercentOutput, liftBackSpeed*0.5);
		}
  	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LinearActuator());
	}

}


