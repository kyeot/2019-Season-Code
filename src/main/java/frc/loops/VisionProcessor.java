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

    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
    NetworkTable visionTable = ntinst.getTable("Vision");
    NetworkTableEntry xEntry = visionTable.getEntry("x");
    NetworkTableEntry yEntry = visionTable.getEntry("y");
    NetworkTableEntry zEntry = visionTable.getEntry("z");
    NetworkTableEntry timeEntry = visionTable.getEntry("timestamp");
    NetworkTableEntry emptyEntry = visionTable.getEntry("empty");

    public static VisionProcessor getInstance() {
        return instance_;
    }

    VisionProcessor() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onLoop() {
        
        NavSensor.getInstance().updateHistory();

        SmartDashboard.putString("DB/String 9", "Gyro Angle: " + Math.floor(NavSensor.getInstance().getAngle(false)));
        SmartDashboard.putString("DB/String 5", "Raw Gyro Angle: " + Math.floor(NavSensor.getInstance().getRawAngle()));

        if(emptyEntry.getBoolean(false)) {
            return;
        }
        
        TargetInfo newTarget = new TargetInfo(
                                        xEntry.getDouble(0), 
                                        yEntry.getDouble(0), 
                                        zEntry.getDouble(0),
                                        emptyEntry.getBoolean(true));

        fieldTransform.addVisionTarget(newTarget, timeEntry.getDouble(RobotController.getFPGATime()));
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
