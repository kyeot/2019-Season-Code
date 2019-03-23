package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.Elevator;
import frc.util.Logger;

/**
 * @purpose: Controlling the Elevator Subsystem
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class ElevatorBase extends Subsystem {

	double out;
	
	//Creates Victor object
	public static TalonSRX elevator;

	DigitalInput elevatorTopSwitch = new DigitalInput(4);

	public ElevatorBase() {

		elevator = new TalonSRX(Constants.kElevatorId);

		elevator.setNeutralMode(NeutralMode.Brake);
		elevator.configOpenloopRamp(Constants.kElevatorRampRate);
	}
	
	//Method to use Elevator base
	public void elevator(double speed) {

		if(!elevatorTopSwitch.get() && speed > 0.1) {
			speed = 0;
		}

		elevator.set(ControlMode.PercentOutput, -speed);

	}

 	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Elevator());
	}

}


