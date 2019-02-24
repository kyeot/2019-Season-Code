package frc.util;

/**
 * Combines a Vector and a Bearing
 * 
 * @author 2783
 * @see Vector
 * @see Bearing
 */
public class Transform {
	
	Vector translation;
	Bearing rotation;
	
	public Transform(double x, double y, double theta) {
		this.translation = new Vector(x, y);
		this.rotation = new Bearing(theta);
	}
	
	public Transform(Vector translation, Bearing rotation) {
		this.translation = translation;
		this.rotation = rotation;
	}
	
	public Transform transform(Transform t) {
		Vector tr = this.translation.translate(t.getTranslation());
		Bearing be = this.rotation.rotate(t.getRotation()); 
		return new Transform(tr, be);
	}
	
	public Vector getTranslation() {
		return this.translation;
	}
	
	public Bearing getRotation() {
		return this.rotation;
	}

}
