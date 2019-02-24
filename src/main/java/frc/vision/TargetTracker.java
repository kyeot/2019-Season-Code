package frc.vision;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import frc.robot.Constants;
import frc.util.Timestamp;
import frc.util.Vector;
import edu.wpi.first.wpilibj.RobotController;

public class TargetTracker {

	Map<Double, Vector> history = new TreeMap<>();

	Object lock = new Object();
	
	public TargetTracker() {
	}
	
	public synchronized void register(Timestamp timestamp, Vector target) {
		history.put(timestamp.getTime(), target);
		update();
	}
	
	/**
	 * Removes targets whose age are larger than the Constant
	 * 
	 * @see Constants.java
	 */
	public synchronized void update() {
		if(isAlive()) {
			ArrayList<Double> toRemove = new ArrayList<Double>();
			for(Double t : history.keySet()) {
				double age = RobotController.getFPGATime()*10E-7 - t;
				if(age > Constants.kTargetMaxAge) {
					toRemove.add(t);
				}
			}
			history.keySet().removeAll(toRemove);
		}
	}
	
	/** 
	 * Returns the most recently registered target to the history.
	 */
	public synchronized Vector getLatestTarget() {
		if(!history.isEmpty()) {
			return history.get(history.keySet().toArray()[history.size()-1]);
		} else {
			return null;
		}
		
		
	}
	
	/**
	 * Averages the entire history of Targets to get a single, smoother target (interpolation)
	 */
	public synchronized Vector getSmoothTarget() {
		double x = 0;
		double y = 0;
		for(Vector v : history.values()) {
			x += v.getA();
			y += v.getB();
		}
		x /= history.size();
		y /= history.size();
		return new Vector(x, y);
	}
	
	/**
	 * Returns a stability value between 0-1
	 * (Team 254's idea)
	 * 
	 * @see Constants.java
	 */
	public synchronized double getStability() {
		return Math.min(1, history.size() / (Constants.kCameraFrameRate * Constants.kTargetMaxAge)); //if theres more frames than targets in the history, its not a stable target
	}
	
	public synchronized boolean isAlive() {
		return !history.isEmpty();
	}
	
}
