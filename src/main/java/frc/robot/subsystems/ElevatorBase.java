package frc.robot.subsystems;

import frc.robot.commands.Elevator;
import frc.util.*;
import frc.robot.*;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @purpose: Controlling the Elevator Subsystem
 * @author Liam Shearin
 * @version 2/7/2019
 */
public class ElevatorBase extends Subsystem {

	double out;
	
	//Creates Victor object
	TalonSRX elevator;

	PIDController pidCont;
	public AbsoluteEncoder elevatorEnc = new AbsoluteEncoder(new AnalogInput(4));
	PIDOutput pidOut;
	ArrayList<Double> angles;

	public ElevatorBase(){
		elevator = new TalonSRX(Constants.kElevatorId);
		
		angles = new ArrayList<Double>();

		elevator.setNeutralMode(NeutralMode.Brake);

		pidOut = new PIDOutput(){
			@Override
			public void pidWrite(double output) {
				out = output;
			}
		};
		pidCont = new PIDController(Constants.kElevateP, Constants.kElevateI, Constants.kElevateD, elevatorEnc, pidOut);
		pidCont.setContinuous(false);
		pidCont.setInputRange(0, 100000);
	}
	
	//Method to use Elevator base
	public void elevator(double speed) {
		if(!pidCont.isEnabled()){
			elevator.set(ControlMode.PercentOutput, -speed);
		}	
	}

	public void setPID(double setpoint){
		update();
		pidCont.setSetpoint(setpoint);
		elevator.set(ControlMode.PercentOutput, out);
		pidCont.enable();
	}

	public boolean withinRange(){
		if(angles.get(0) < 1 && angles.get(9) < 1){
			return true;
		}
		else{
			return false;
		}
	}

	public void update(){
		if(pidCont.isEnabled()){
			angles.add(pidCont.getError());
			angles.remove(10);
		}
		else{
			angles.add(5.0);
		}
	}

 	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Elevator());
	}

}


