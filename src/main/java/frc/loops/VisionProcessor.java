package frc.loops;

import frc.robot.FieldTransform;
import frc.util.NavSensor;
import frc.vision.TargetInfo;
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
    NetworkTableEntry emptyEntry;

    double time0;
    boolean firstTime = true;

    public static VisionProcessor getInstance() {
        return instance_;
    }

    VisionProcessor() {
    }

    @Override
    public void onStart() {
        ntinst = NetworkTableInstance.getDefault();
        chickenTable = ntinst.getTable("ChickenVision");

        xEntry = chickenTable.getEntry("tapeX");
        yEntry = chickenTable.getEntry("tapeY");
        zEntry = chickenTable.getEntry("tapeZ");
        timeEntry = chickenTable.getEntry("VideoTimestamp");
        emptyEntry = chickenTable.getEntry("tapeDetected");
    }

    @Override
    public void onLoop() { 
        
        NavSensor.getInstance().updateHistory();

        if(firstTime) {
            if(timeEntry.getDouble(-1) == -1) {
                return;
            } else {
                time0 = timeEntry.getDouble(-1)*10E-7;
                SmartDashboard.putString("DB/String 4", "" + time0);
                firstTime = false;
            }
        }
        
        if(!emptyEntry.getBoolean(false)) {
            return;
        }
       
        
        TargetInfo newTarget = new TargetInfo(
                                        xEntry.getDouble(0), 
                                        yEntry.getDouble(0), 
                                        zEntry.getDouble(0),
                                        !emptyEntry.getBoolean(false));

        fieldTransform.addVisionTarget(newTarget, timeEntry.getDouble(-1) - time0);
        fieldTransform.trackLatestTarget();

        
    }

    @Override
    public void onStop() {
        // no-op
    }

    @Override
    
    public void onLoop(double timestamp) {

    }

}
