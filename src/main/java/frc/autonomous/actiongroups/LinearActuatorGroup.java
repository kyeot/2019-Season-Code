/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actiongroups;

import frc.autonomous.ActionGroup;
import frc.autonomous.actions.*;

/**
 * linear actuator climb auto
 */
public class LinearActuatorGroup extends ActionGroup {

	public LinearActuatorGroup(){
		super();

		addAction(new AutoLA(0, 0.15, 0, 0.25));
		addAction(new AutoLA(1, 1, 0, 3));
		addAction(new AutoLA(0, 0, 1, 3));
		addAction(new AutoLA(-1, 0, 0, 3));
		addAction(new AutoDrive(180, 0.25, 0, 2));
		addAction(new AutoLA(0, -1, 0, 6));
		addAction(new AutoDrive(180, 0.25, 0, 1));
	
	}


}
