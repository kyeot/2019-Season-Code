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
public class AutoElevator extends Action {

    double speed;

    public AutoElevator(double speed, double time) {
        super("AutoElevator", time);

        this.speed = speed;
    }

    @Override
    public void perform() {
        Robot.elevatorBase.elevator(speed);
    }

    @Override
    public void finish() {
        Robot.elevatorBase.elevator(0);
    }

}
