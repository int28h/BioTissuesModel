package Components;

public class Plate {
	private static int angleOfRotation;
	private static double depolarization;
	private static int thickness;
	
	public Plate(int angleOfRotationStart, int angleOfRotationStop, 
			double depolarizationLimit) {
		this.angleOfRotation = (int) (angleOfRotationStart + (Math.random() * angleOfRotationStop));
		this.depolarization = 0 + (Math.random() * depolarizationLimit);
		this.thickness = ((int) (200 + (Math.random() * 500))) * 1000; //мкм переводятся в нм		
	}
	
	public int getThickness() {
		return this.thickness;
	}
	
	public int getAngle() {
		return this.angleOfRotation;
	}
	
	public double getDepolarization() {
		return this.depolarization;
	}
}
