package Components;

import java.util.Random;

public class Plate {
	private double angleOfRotation;
	private int thickness;
	
	Random random = new Random();
	
	/**
	 * Угол поворота и толщина фиксированные.
	 * @param angleOfRotation
	 * @param thickness
	 */
	public Plate(int angleOfRotation, int thickness) {
		this.angleOfRotation = angleOfRotation * 0.017453; // в радианах
		this.thickness = thickness * 1000; //мкм переводятся в нм		
	}
	
	/**
	 * Угол поворота с разбросом в некотором задаваемом диапазоне.
	 * Толщина фиксированная.
	 * @param angleOfRotation
	 * @param difference
	 * @param thickness
	 */
	public Plate(int angleOfRotation, int differenceForAngles, int thickness) {		
		this.angleOfRotation = (angleOfRotation + random.nextInt(differenceForAngles++ + differenceForAngles) - 1) * 0.017453; // в радианах
		this.thickness = thickness * 1000; 	
	}
	
	/**
	 * Угол поворота с разбросом в некотором задаваемом диапазоне.
	 * Толщины с разбросом +-50 мкм.
	 * @param angleOfRotation
	 * @param differenceForAngles
	 * @param thickness
	 * @param isThicknessWithFlucuations
	 */
	public Plate(int angleOfRotation, int differenceForAngles, int thickness, boolean isThicknessWithFlucuations) {
		this.angleOfRotation = (angleOfRotation + random.nextInt(differenceForAngles++ + differenceForAngles) - differenceForAngles) * 0.017453; // в радианах
		this.thickness = (thickness + random.nextInt(51 + 50) - 50) * 1000;
	}
	
	/**
	 * Угол поворота фиксированный.
	 * Толщины с разбросом +-50 мкм.
	 * @param angleOfRotation
	 * @param thickness
	 * @param isThicknessWithFlucuations
	 */
	public Plate(int angleOfRotation, int thickness, boolean isThicknessWithFlucuations) {
		this.angleOfRotation = angleOfRotation * 0.017453;
		this.thickness = (thickness + random.nextInt(51 + 50) - 50) * 1000;
	}
	
	public int getThickness() {
		return this.thickness;
	}
	
	public double getAngle() {
		return this.angleOfRotation;
	}
}
