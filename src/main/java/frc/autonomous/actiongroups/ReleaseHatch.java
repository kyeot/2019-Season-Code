/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actiongroups;

import frc.autonomous.ActionGroup;
import frc.autonomous.actions.AutoElevator;

/**
 * Add your docs here.
 */
public class ReleaseHatch extends ActionGroup {
    
    public ReleaseHatch() {
        super();

        addAction(new AutoElevator(-0.4, 0.16));
    }

}

