/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.loops.EncoderCounter;

/**
 * Add your docs here.
 */
public class ElevatorEncoder implements PIDSource {
	PIDSourceType sourceType;

	public ElevatorEncoder(){
		setPIDSourceType(PIDSourceType.kDisplacement);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		sourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}

	@Override
	public double pidGet(){
		return EncoderCounter.getAngle();
	}

}
