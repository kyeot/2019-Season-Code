/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actiongroups;

import frc.autonomous.actions.ReleaseServo;
import frc.autonomous.*;

/**
 * Add your docs here.
 */
public class ServoGroup extends ActionGroup{

	public ServoGroup(){
		super();

		addAction(new ReleaseServo(0, 0.5));
		addAction(new ReleaseServo(180, 0.5));
		addAction(new ReleaseServo(0, 0.5));
	}
}