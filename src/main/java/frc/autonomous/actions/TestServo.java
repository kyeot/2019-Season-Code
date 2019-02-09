/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actions;

import frc.autonomous.*;
import frc.robot.*;

/**
 * Add your docs here.
 */
public class TestServo extends Action {
	
	double angle;
	
	//drives autonomously
	public TestServo(double angle, double time) {
		super("AutoDrive", time);
		
		this.angle = angle;
	}
	
	@Override
	public void perform() {
		Robot.intakeBase.testServo(angle);
    }
    
}
