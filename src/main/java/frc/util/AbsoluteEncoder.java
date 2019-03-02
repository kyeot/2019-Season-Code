/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class AbsoluteEncoder implements PIDSource {
    PIDSourceType sourceType;

    AnalogInput enc;

    double offset = 0;
    double revolutions = 0;

    double lastAngle;
    double startAngle;
    double angle;

    public AbsoluteEncoder(AnalogInput enc){
        this.enc = enc;
        sourceType = PIDSourceType.kDisplacement;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        sourceType = pidSource;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return sourceType;
    }

    public double getRawAngle(){
        return enc.getValue();
    }

    @Override
    public double pidGet(){
        startAngle = getRawAngle();

        if((startAngle > 0 && startAngle < 1000) && (lastAngle > 3096 && lastAngle < 4096)){
            revolutions++;
        }
        else if((startAngle > 3096 && startAngle < 4096) && (lastAngle > 0 && lastAngle < 1000)){
            revolutions--;
        }

        angle = startAngle + (revolutions*4096);

        lastAngle = startAngle;
        return angle - offset;
    }

    public void reset(){
        offset = enc.getValue();
    }

}
