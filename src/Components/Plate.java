package Components;

import java.util.Random;

public class Plate {
	private double angleOfRotation;
	private double depolarization;
	private int thickness;
	
	Random random = new Random();
	
	public Plate(int angleOfRotationStart, int angleOfRotationStop, 
			double depolarizationLimit) {
		this.angleOfRotation = (angleOfRotationStart + random.nextInt(angleOfRotationStop++ - angleOfRotationStart)) * 0.017453; // в радианах
		this.depolarization = 0 + depolarizationLimit * random.nextDouble();
		this.thickness = (200 + random.nextInt(501 - 200)) * 1000; //мкм переводятся в нм		
	}
	
	public Plate(int angleOfRotationStart, int angleOfRotationStop, 
			double depolarizationLimit, int fixedThickness) {
		this.angleOfRotation = (angleOfRotationStart + random.nextInt(angleOfRotationStop++ - angleOfRotationStart)) * 0.017453; // в радианах
		this.depolarization = 0 + depolarizationLimit * random.nextDouble();
		this.thickness = fixedThickness * 1000; //мкм переводятся в нм		
	}
	
	public int getThickness() {
		return this.thickness;
	}
	
	public double getAngle() {
		return this.angleOfRotation;
	}
	
	public double getDepolarization() {
		return this.depolarization;
	}
}
