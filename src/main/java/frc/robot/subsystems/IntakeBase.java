package frc.robot.subsystems;

import frc.robot.commands.Elevator;
import frc.robot.commands.Intake;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @purpose: Controlling the Intake Subsystem
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class IntakeBase extends Subsystem {
	
	//Creates Victor object
	VictorSPX intake;


	public IntakeBase(){
		intake = new VictorSPX(Constants.kIntakeId);

	
		intake.setNeutralMode(NeutralMode.Brake);

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


