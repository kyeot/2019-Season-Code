package frc.util;

import edu.wpi.first.wpilibj.RobotController;

public class Timestamp implements Comparable<Timestamp>{

	Double t;
	
	public Timestamp(double t) {
		this.t = t;
	}
	
	public static Timestamp setNewTime() {
		return new Timestamp(RobotController.getFPGATime()*10E-7);
	}
	
	public Double getTime() {
		return t;
	}
	
	public Double getAge() {
		return RobotController.getFPGATime()*10E-7 - t;
	}

	@Override
	public int compareTo(Timestamp o) {
		// TODO Auto-generated method stub
		return o.getAge().compareTo(this.t);
	}
	
}
