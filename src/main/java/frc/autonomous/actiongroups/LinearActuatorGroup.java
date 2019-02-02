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
public class LinearActuatorGroup extends ActionGroup {

    public LinearActuatorGroup(){
        super();

        // addAction(new AutoLinearActuator(speed, time));
        addAction(new AutoLaUP(1, 2));
        addAction(new AutoLaHold(.15,5));
        

    
    }

}
