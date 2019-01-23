package frc.vision;

/**
 * A container class for Targets detected by the vision system, containing the
 * location in three-dimensional space.
 * 
 * @author 254
 */
public class TargetInfo {
    protected double x;
    protected double y;
    protected double z;
    protected boolean empty;

    public TargetInfo(double x, double y, double z, boolean empty) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.empty = empty;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public boolean isEmpty() {
        return empty;
    }
}
