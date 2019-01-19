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
 * Add your docs here.
 */
public class TestGroup extends ActionGroup{

    public TestGroup(){
        super();

        addAction(new AutoDrive(0, 0.5, 0, 3));

    }

}
