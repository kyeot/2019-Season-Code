/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import java.util.ArrayList;

/**
 * Add your docs here.
 */
public class ActionGroup {

	ArrayList<Action> actions = new ArrayList<Action>();
	ArrayList<Action> actions2 = new ArrayList<Action>();
	
	public ActionGroup() {
		actions = new ArrayList<Action>();
		actions2 = new ArrayList<Action>();
	}
	
	protected void addAction(Action action) {
		actions.add(action);
	}
	
	protected void addTandemAction(Action action) {
		actions2.add(action);
	}
	
	public ArrayList<Action> getActions() {
		return actions;
	}
	
	public ArrayList<Action> getTandemActions() {
		return actions2;
    }
    
}
