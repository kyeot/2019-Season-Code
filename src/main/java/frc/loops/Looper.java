package frc.loops;

import java.util.List;
import java.util.ArrayList;

import frc.loops.Loop;
import frc.robot.Constants;
import frc.util.CrashTrackingRunnable;
import frc.util.Logger;

import edu.wpi.first.wpilibj.Notifier;

/**
 *
 * Loops the loops
 *
 */
public class Looper {
	
	double period;
	private final Object taskRunningLock_ = new Object();
	
	CrashTrackingRunnable runnable = new CrashTrackingRunnable() {
		@Override
		public void runCrashTracked() {
			for(Loop l : loops) {
				l.onLoop();
			}
		}
		
		@Override
		public void logCrash() {
			Logger.error("Exception caught in Loops");
		}
	};
	
	List<Loop> loops;
	Notifier notifier;
	
	public Looper(double period) {
		this.period = period;
		loops = new ArrayList<Loop>();
		notifier = new Notifier(runnable);
	}
	
	public void startLoops() {
		for(Loop l : loops) {
			l.onStart();
		}
		notifier.startPeriodic(Constants.kLoopPeriod);
		
	}
	
	public void addLoop(Loop l) {
		loops.add(l);
	}
	
	public void stopLoops() {
		notifier.stop();
		for(Loop l : loops) {
			l.onStop();
		}
	}
	
	public synchronized void register(Loop loop) {
        synchronized (taskRunningLock_) {
            loops.add(loop);
        }
    }

}
