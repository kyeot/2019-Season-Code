package frc.util;

public abstract class CrashTrackingRunnable implements Runnable {
	
	@Override
	public void run() {
		try {
			runCrashTracked();
		} catch(Throwable t) {
			Logger.error("Exception caught in Loops");
			logCrash();
			throw(t);
		}
	}
	
	public abstract void runCrashTracked();
	
	public abstract void logCrash();
}
