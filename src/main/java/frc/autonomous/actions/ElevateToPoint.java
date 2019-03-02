
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actions;

import frc.autonomous.Action;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ElevateToPoint  extends Action {
	
	double angle;

	public ElevateToPoint(double angle) {
		super("AutoElevate");
		
		this.angle = angle;
		
	}
	
	@Override
	public void perform() {
        Robot.elevatorBase.setPID(angle);
    }

    @Override
    public boolean done() {
        return Robot.elevatorBase.withinRange();
    }
}
