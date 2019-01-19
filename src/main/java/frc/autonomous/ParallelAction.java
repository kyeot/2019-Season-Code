package frc.autonomous;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite action, running all sub-actions at the same time All actions are started then updated until all actions
 * report being done.
 * 
 * @param A List of Action objects
 */

public class ParallelAction extends Action {
	
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	public ParallelAction(List<Action> actions) {
		
		super("Parallel actions");
		this.actions = new ArrayList<>(actions.size());
        for (Action action : actions) {
            this.actions.add(action);
        }
		
	}
	
	//Waits for ALL actions to be done
	@Override
	public boolean done() {
		boolean all_finished = true;
        for (Action action : actions) {
            if (!action.done()) {
                all_finished = false;
            }
        }
        return all_finished;
	}
	//When called once, all actions in the list run
	@Override
	public void perform() {
		for (Action action : actions) {
            action.perform();
        }
	}
	
	//Finishes all actions at once
	@Override
	public void finish() {
		for (Action action : actions) {
            action.finish();
        }
	}
	
	//Starts all actions at once
	@Override
	public void start() {
		for (Action action : actions) {
            action.start();
        }
	}
	
}
