package frc.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import frc.robot.Constants;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SPI;

/**
 * Singleton class for getting the angle read by the gyro sensor mounted on the roborio,
 * can also store a history of poses in a Map class
 *
 * @author 2783
 */
public class NavSensor {
	static NavSensor gyro = new NavSensor();
	
	public static NavSensor getInstance() {
		return gyro;
	}
	
	private boolean isNegative(double possibleNegativeValue) {
		return possibleNegativeValue < 0;
	}
	
	private double findTerm(int termNumber) {
		return (double) history.keySet().toArray()[termNumber];
	}
	
	public Map<Double, Bearing> history = new TreeMap<Double, Bearing>();
	
	NavSensor() {
		try {
	         navSensor = new AHRS(SPI.Port.kMXP);
	     } catch (RuntimeException ex ) {
	         DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
	     }
	}
	
	AHRS navSensor;
	double offset = 0;
	
	public double getAngle(boolean reversed) {
    	if(reversed) {
    		return 360-((((navSensor.getAngle()+180.0)%360)+360)%360); //deals with negative angles, java 8 simply keeps the sign when modulating negative values.
    	} else {
    		return 360-(((navSensor.getAngle()%360)+360)%360);
    	}
    }
	
	public double getRawAngle() {
		return navSensor.getAngle();
	}
	
	public void resetGyroNorth(double angle, double north) {
    	navSensor.reset();
    	offset = angle - north;
    }
	
	public void updateHistory() {
		history.put(Timestamp.setNewTime().getTime(), new Bearing(getAngle(false)));
		ArrayList<Double> toRemove = new ArrayList<Double>();
		for(Double t : history.keySet()) {
			double age = RobotController.getFPGATime()*10E-7 - t;
			if(age > Constants.kGyroMaxAge) {
				toRemove.add(t);
			}
		}
		history.keySet().removeAll(toRemove);
	}
	
	public Bearing getAngleAtTime(Timestamp time) {
		
		//Initial setting of variables
		double desiredTime = time.getTime();
		double numberOfTerms = history.keySet().size();
		double previousTerm = 0;
		int counter = 0;
		
		//Finding middle of set, rounded down to the nearest term
		double referenceTerm = (numberOfTerms / 2) - ((numberOfTerms / 2) % 1);
		
		//Finding reference state
		boolean referenceState = isNegative(desiredTime - findTerm(0));
		
		//Search for closest time to desired time
		if(history.get(desiredTime) != null) {
			//Desired time exists in array
			return history.get(desiredTime);
		} else {
			//Desired time does not exist, search for closest time
			boolean comparison = false;
			
			//Find closest time by sign changes in halves
			while(Math.abs(previousTerm - referenceTerm) != 1) {
				if(referenceState == isNegative(desiredTime - findTerm((int) referenceTerm))) {
					//Closest time is in upper half of series bounded by relative maxima and minima
					previousTerm = referenceTerm;
					referenceTerm = referenceTerm + ((referenceTerm / 2) - ((referenceTerm / 2) % 1));
					counter++;
				} else {
					//Closest time is in lower half of series bounded by relative maxima and minima
					previousTerm = referenceTerm;
					referenceTerm = ((referenceTerm / 2) - ((referenceTerm / 2) % 1));
					counter++;
				}
			}
			//Compare previousTerm and referenceTerm to find the closer time
			if((Math.abs((findTerm((int) previousTerm) - desiredTime))) >
			   (Math.abs((findTerm((int) referenceTerm) - desiredTime)))) {
				return history.get(findTerm((int) referenceTerm));
			} else {
				return history.get(findTerm((int) previousTerm));
			}
		}
	}

}