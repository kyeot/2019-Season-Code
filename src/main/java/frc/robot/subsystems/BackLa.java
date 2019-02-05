package frc.robot.subsystems;
import frc.robot.commands.LinearActuator;
/**
 * @purpose: Linear Actuator Base
 * @author Liam Shearin
 * @version 1/28/2019
 */
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BackLa extends Subsystem {
	
	//Creates Victor object
//	VictorSPX back;
	
	TalonSRX back;

	public BackLa(){
		
	//	back = new VictorSPX(frc.robot.Constants.kBackLinearActuatorId);
	

	back = new TalonSRX(frc.robot.Constants.kBackLinearActuatorId);
	


	  back.setNeutralMode(NeutralMode.Brake);
		
	}
	
	//Method to use Linear Actuator base
	public void linearActuator(double speed) {

		back.set(ControlMode.PercentOutput, speed);
		
  }

	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LinearActuator());
		
	}

}


