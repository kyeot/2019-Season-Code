package frc.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;

/**
 * Singleton class for getting the angle read by the gyro sensor mounted on the
 * roborio, can also store a history of poses in a Map class
 *
 * @author 2783
 */
public class NavSensor {
	static NavSensor gyro = new NavSensor();

	Object lock;

	public static NavSensor getInstance() {
		return gyro;
	}
	private double findTerm(int termNumber) {
		if (termNumber >= history.keySet().size()) {
			return -1;
		}
		return (double) history.keySet().toArray()[termNumber];
	}

	public Map<Double, Bearing> history = new TreeMap<Double, Bearing>();

	NavSensor() {
		try {
			navSensor = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}

	public AHRS navSensor;
	double offset = 0;

	public double getAngle(boolean reversed) {
    	if(reversed) {
    		return 360 - (navSensor.getAngle()+Constants.kRobotFront + offset)%360; //deals with negative angles, java 8 simply keeps the sign when modulating negative values.
    	} else {
    		return (navSensor.getAngle()+offset)%360;
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
		history.put(Timestamp.setNewTime().getTime(), new Bearing(getAngle(true)));
		ArrayList<Double> toRemove = new ArrayList<Double>();
		for (Double t : history.keySet()) {
			double age = RobotController.getFPGATime() * 10E-7 - t;
			if (age > Constants.kGyroMaxAge) {
				toRemove.add(t);
			}
		}
		history.keySet().removeAll(toRemove);
	}

	public Bearing getAngleAtTime(Timestamp time) {
		double index = Double.MAX_VALUE;
		int low = 0;
		int high = history.keySet().size() - 1;

		if (time.getTime() < findTerm(low)) {
			return history.get(findTerm(low));
		}

		while (low <= high) {
			int mid = (low + high) / 2;
			// If the middle term is less than the desired value
			if (findTerm(mid) < time.getTime()) {
				// If the desired term is in between the middle term and the next term above
				if (findTerm(mid + 1) > time.getTime() || findTerm(mid + 1) == -1) {
					// If desired term is closer to middle term
					if (findTerm(mid + 1) - time.getTime() > time.getTime() - findTerm(mid)) {
						// return that angle
						index = mid;
						break;
					} else {
						index = mid + 1;
						break;
					}
				}
				low = mid + 1;
			} else if (findTerm(mid) > time.getTime()) {
				// If the desired term is in between the middle term and the next term below
				if (findTerm(mid - 1) < time.getTime() || findTerm(mid - 1) == -1) {
					// If desired term is closer to middle term
					if (findTerm(mid) - time.getTime() > time.getTime() - findTerm(mid - 1)) {
						// return that angle
						index = mid - 1;
						break;
					} else {
						index = mid;
						break;
					}
				}
				high = mid - 1;
			} else {
				return history.get(findTerm(mid));
			}
		}
		return history.get(findTerm((int) index));
	}

}