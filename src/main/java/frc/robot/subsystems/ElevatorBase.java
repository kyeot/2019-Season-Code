package frc.robot.subsystems;

import frc.robot.commands.Elevator;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @purpose: Controlling the Elevator Subsystem
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class ElevatorBase extends Subsystem {
	
	//Creates Victor object
	TalonSRX elevator;

	public ElevatorBase(){
		elevator = new TalonSRX(Constants.kElevatorId);
	
		elevator.setNeutralMode(NeutralMode.Brake);

	}
	
	//Method to use Elevator base
	public void elevator(double speed) {
		elevator.set(ControlMode.PercentOutput, -speed);

  }

  @Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Elevator());
	}

}


