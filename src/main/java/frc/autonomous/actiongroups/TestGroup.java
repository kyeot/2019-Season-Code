/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actiongroups;

import java.util.Arrays;

import frc.autonomous.ActionGroup;
import frc.autonomous.ParallelAction;
import frc.autonomous.actions.*;
import frc.autonomous.*;

/**
 * Add your docs here.
 */
public class TestGroup extends ActionGroup{

    public TestGroup(){
        super();

        addAction(new AutoDrive(0, 0.5, 0, 3));
        addAction(new ParallelAction((Arrays.asList(new Action[] {
            new AutoLaUP(6, 7),	
            new AutoLaHold(-0.8, 2.5)
        }))));;
    }



}
