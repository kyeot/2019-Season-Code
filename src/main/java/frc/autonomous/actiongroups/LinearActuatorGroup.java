/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous.actiongroups;

import frc.autonomous.ActionGroup;
import frc.autonomous.actions.*;
import java.util.Arrays;

import frc.autonomous.ParallelAction;
import frc.autonomous.*;

/**
 * linerar actuator climb auto
 */
public class LinearActuatorGroup extends ActionGroup {

    public LinearActuatorGroup(){
        super();

        addAction(new AutoLA(1, 1, 0, 2));
        addAction(new ParallelAction((Arrays.asList(new Action[] {
            new AutoLA(0.15, 0.15, 1, 5),
            new SwerveAlign(90, 2)
        }))));;
        addAction(new AutoLA(-1, 0.15, 0, 1.8));
        addAction(new AutoDrive(0, .5, 0, 3));
        addAction(new AutoLA (0, -1, 0, 1.8));
    
    }


}
