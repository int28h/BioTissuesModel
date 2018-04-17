package Components;

import java.util.Random;

public class Plate {
	private double angleOfRotation;
	private int thickness;
	
	Random random = new Random();
	
	public Plate(int angleOfRotation, int thickness) {
		this.angleOfRotation = angleOfRotation * 0.017453; // в радианах
		this.thickness = thickness * 1000; //мкм переводятся в нм		
	}
	
	public int getThickness() {
		return this.thickness;
	}
	
	public double getAngle() {
		return this.angleOfRotation;
	}
}
