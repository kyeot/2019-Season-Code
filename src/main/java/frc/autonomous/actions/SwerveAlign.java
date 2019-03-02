/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actions;

import frc.autonomous.Action;
import frc.robot.subsystems.SwerveController;
import frc.util.Bearing;

/**
 * Add your docs here.
 */
public class SwerveAlign extends Action {

	double angle;

	public SwerveAlign(double angle, double time){
		super("SwerveAlign", time);
	
		this.angle = angle;
	}

	@Override 
	public void perform(){
		SwerveController.getInstance().setPose(new Bearing(angle));
	}

}

