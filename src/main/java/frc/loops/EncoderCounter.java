/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.loops;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class EncoderCounter implements Loop{

	static EncoderCounter encoderCounter = new EncoderCounter();

	public static EncoderCounter getInstance(){
		return encoderCounter;
	}

	public static AnalogInput enc = new AnalogInput(5);

	public static double offset = 0;
	static double startAngle;
			
	static double lastAngle = 0;
	public static double revolutions;
	public static double angle;

	boolean up;
	
	public EncoderCounter(){
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onLoop() {
		startAngle = getValueFromEdge();

		if((startAngle < 100 && up) && (lastAngle < 100 && !up)){
			revolutions++;
		}
		else if((startAngle < 100 && !up) && (lastAngle < 100 && up)){
			revolutions--;
		}

		angle = startAngle + (revolutions*Constants.kElevatorEncRange);

		lastAngle = startAngle;

		System.out.println(getAngle());
	}

	public double getValueFromEdge(){
		double ang = getRawAngle();

		if(ang <= Constants.kElevatorEncRange){
			ang -= 155;
			up = false;
		}
		else{
			ang = 850-ang;
			up = true;
		}

		return ang;
	}

	@Override
	public void onStop() {
	}

	public double getRevolutions(){
		return revolutions;
	}
	
	public static double getRawAngle(){
		return enc.getValue();
	}
	
	public static double getAngle(){
		return angle;
	}

	public static void zero(){
		offset = angle;
	}

	@Override
	public void onLoop(double timestamp) {
	}
    
}
