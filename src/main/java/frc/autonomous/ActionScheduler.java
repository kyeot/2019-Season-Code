package frc.autonomous;

import java.util.ArrayList;

import frc.robot.Constants;
import frc.util.CrashTrackingRunnable;
import frc.util.Logger;

import edu.wpi.first.wpilibj.Notifier;

public class ActionScheduler {
	
	//The Action action is what runs
	//The ArrayList queue is what organizes the action being run
	Notifier thread;
	Action action;
	
	boolean active = false;
	
	ArrayList<Action> queue;
	
	public ActionScheduler() {
		//The list of actions in a queue
		queue = new ArrayList<Action>();
		
		thread = new Notifier(new CrashTrackingRunnable() {
			
			//t=this method is what actually runs the current action set by setAuto
			@Override
			public void runCrashTracked() {
				
				if(isActive()) {
					//Loops current action
					action.perform();

					//Checks if the "done" has returned true each loop
					if(action.done()) {
						//Ends the current action if "done" returns true
						action.finish();
						Logger.info("Action " + action.getId() + " has finished and quit");
						
						//Runs the next action in queue if there is one, if not, ends the scheduler
						if(!queue.isEmpty()) {
							setAuto(queue.get(0));
							queue.get(0).start();
							queue.remove(0);
						} else {
							stop();
						}
						
					}
					//Checks each loop if the action has failed
					if(action.fail()) {
						//Logs that an action has failed
						Logger.error("Action " + action.getId() + " has failed and quit");

						//Runs the next action in queue if there is one, if not, ends the scheduler
						if(!queue.isEmpty()) {
							setAuto(queue.get(0));
							queue.get(0).start();
							queue.remove(0);
						} else {
							stop();
						}
					}
				}
				
			} 
			
			//Stops the scheduler and logs a crash when it crashes
			@Override
			public void logCrash() {
				Logger.error("Exception caught in Actions: " + action.getId());
				stop();
			}
		});
	}
	
	//Sets the currently running action
	//This will soon end and the method will be called again to add another action
	public void setAuto(Action action) {
		this.action = action;
	}
	
	//Sets the running group to a given Action Group
	public void setGroup(ActionGroup group) {
		//Queues every action of the set action group, in order
		for(Action a : group.getActions()) {
			queue(a);
		}
	}
	
	public void queue(Action action) {
		queue.add(action);
	}

	public void wipe(){
		queue.clear();
	}
	
	//Method to start a given Action
	public void start() {
		//Makes sure queue isn't empty, gets the first added action and removes it from the list
		if(!queue.isEmpty()) {
			setAuto(queue.get(0));
			queue.remove(0);
		}
		//Makes sure the action isn't null, starts it
		if(action != null) {
			action.start();
			thread.startPeriodic(Constants.kPeriod);
			active = true;
		}
		//If the queue was empty or the action was null, logs a warning
		else {
			Logger.warn("No Action to Start the Scheduler");
		}
		
	}
	
	//Checks if the scheduler is active and if so, stops it
	public void stop() {
		if(isActive()) {
			thread.stop();
			active = false;
		}
	}
	
	//Returns wether or not the scheduler is active
	public boolean isActive() {
		return active;
	}
}