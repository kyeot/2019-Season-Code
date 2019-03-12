package frc.loops;

import frc.robot.FieldTransform;
import frc.util.NavSensor;
import frc.vision.TargetInfo;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This function adds vision updates (from the Nexus smartphone) to a list in
 * RobotState. This helps keep track of goals detected by the vision system. The
 * code to determine the best goal to shoot at and prune old Goal tracks is in
 * GoalTracker.java
 * 
 * @author 254
 */
public class VisionProcessor implements Loop {
	static VisionProcessor instance_ = new VisionProcessor();
	FieldTransform fieldTransform = FieldTransform.getInstance();

	NetworkTableInstance ntinst;
	NetworkTable chickenTable;
	NetworkTableEntry xEntry;
	NetworkTableEntry yEntry;
	NetworkTableEntry zEntry;
	NetworkTableEntry timeEntry;
	NetworkTableEntry detectedEntry;
	NetworkTableEntry doSynchronize;
	NetworkTableEntry gotSynchronize;

	double time0 = 0;
	double offset = 0;

	boolean firstTime;
	int foo = 0;

	public static VisionProcessor getInstance() {
		return instance_;
	}

	VisionProcessor() {
	}

	@Override
	public void onStart() {
		firstTime = true;
		ntinst = NetworkTableInstance.getDefault();
		chickenTable = ntinst.getTable("ChickenVision");

		xEntry = chickenTable.getEntry("tapeX");
		yEntry = chickenTable.getEntry("tapeY");
		zEntry = chickenTable.getEntry("tapeZ");
		timeEntry = chickenTable.getEntry("VideoTimestamp");
		detectedEntry = chickenTable.getEntry("tapeDetected");
		doSynchronize = chickenTable.getEntry("doSynchronize");

		chickenTable.addEntryListener("gotSynchronized", (table, key, entry, value, flags) -> {
			offset = (((time0 + (RobotController.getFPGATime()*10E-7)) / 2) - value.getDouble());
		}, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

		
	}

	@Override
	public void onLoop() {

		NavSensor.getInstance().updateHistory();

		//wait 3 seconds, then synchronize clocks
		if(firstTime && (RobotController.getFPGATime()*10E-7 > (3 * foo))) {
			time0 = RobotController.getFPGATime()*10E-7;
			doSynchronize.setDouble(time0);
			ntinst.flush();
			foo++;
			return;
		}

		if(offset == 0)  {
			SmartDashboard.putString("DB/String 0", "Synchronizing clocks...");
			return;
		}
		
		if(!isTargetDetected()) {
			SmartDashboard.putString("DB/String 0", "NO TARGETS FOUND");
			return;
		}
	   
		//Incoming vector in right-handed system
		TargetInfo newTarget = new TargetInfo(
										xEntry.getDouble(0), 
										yEntry.getDouble(0), 
										zEntry.getDouble(0),
										timeEntry.getDouble(-1)*10E-7 + offset);

		fieldTransform.addVisionTarget(newTarget);
		fieldTransform.trackLatestTarget();

		SmartDashboard.putString("DB/String 0", "Angle to Target: " + Math.floor(fieldTransform.targetHistory.getSmoothTarget().dir().getTheta()));
		
	}

	@Override
	public void onStop() {
		// no-op
	}

	@Override
	public void onLoop(double timestamp) {

	}

	public boolean isTargetDetected() {
		return detectedEntry.getBoolean(false);
	}

}
