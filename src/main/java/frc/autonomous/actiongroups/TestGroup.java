/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actiongroups;

import frc.autonomous.ActionGroup;
import frc.autonomous.actions.*;
import frc.util.Bearing;

/**
 * Add your docs here.
 */
public class TestGroup extends ActionGroup{

    public TestGroup(){
        super();

        addAction(new RotateToAngle(new Bearing(0), 5));

    }

}
