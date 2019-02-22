/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import java.util.UUID;

import frc.util.Timer;

/**
 * Add your docs here.
 */
public class Action {
	
	String id;
	
	protected Timer timer;
	boolean timed;
	
	public Action(String id, double time) {
		this.id = id + ":" + UUID.randomUUID().toString();
		
		timer = new Timer(time);
		timed = true;
	}
	
	public Action(String id) {
		this.id = id + ":" + UUID.randomUUID().toString();
		
		timed = false;
	}
	
	//Where you put code that runs at the start of the action, if it's timed it automatically starts the timer here
	public void start() {
		if(timed) {
			timer.start();
		}
	}
	
	//The main loop, runs on repeat based on period value in Constants
	public void perform() {
		
	}
	
	//Make this return true when you want the action to end, if it's time this will automatically return true when the timer rings
	public boolean done() {
		if(timed) {
			return timer.ring();
		}
		return false;
	}
	
	//Runs once after the Action ends
	public void finish() {
		
	}
	
	public boolean fail() {
		return false;
	}

	//Returns the Action ID set in constructor
	public String getId() {
		return id;
	}
}
