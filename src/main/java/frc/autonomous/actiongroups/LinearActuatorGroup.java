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
 * linear actuator climb auto
 */
public class LinearActuatorGroup extends ActionGroup {

    public LinearActuatorGroup(){
        super();

        addAction(new AutoLA(0, 0.5, 0, 1));
        addAction(new AutoLA(0.5, 0.5, 0, 2));
    
    }


}
