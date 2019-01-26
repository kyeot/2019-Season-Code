/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actions;

import frc.robot.subsystems.SwerveController;
import frc.autonomous.*;
import frc.util.*;

/**
 * Add your docs here.
 */
public class RotateToAngle extends Action{

    Bearing b;

	public RotateToAngle(Bearing b, double time) {
		super("RotateTankAction", time);
		
		this.b = b;
	}
	
	@Override
	public void perform(){
        SwerveController.getInstance().setPose(new Bearing(0));
        
	}
}
