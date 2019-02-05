package frc.robot.subsystems;
import frc.robot.commands.LaDrive;
/**
 * @purpose: Linear Actuator Drive Base
 * @author Liam Shearin
 * @version 2/2/2019
 */
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LaDriveBase extends Subsystem {
	
	//Creates Victor object
//	VictorSPX drive;

	
	TalonSRX drive;


	public LaDriveBase(){
		
	//	drive = new VictorSPX(frc.robot.Constants.kDriveLinearActuatorId);

	
	drive = new TalonSRX(frc.robot.Constants.kDriveLinearActuatorId);

	

		drive.setNeutralMode(NeutralMode.Brake);

		
	}
	
	//Method to use Linear Actuator drive
	public void laDrive(double speed) {
		drive.set(ControlMode.PercentOutput, speed);
	
		
  }

	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LaDrive());
		
	}

}


