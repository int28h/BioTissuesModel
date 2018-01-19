import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import Components.Plate;
import Components.Polarizer;

public class Main {	
	private static final int PLATES_COUNT = 10;
	private static final int WAVELENGTH_START = 400;
	private static final int WAVELENGTH_STOP = 400;
	private static final double DIFFERENCE = 0.001; //разница между обыкновенным и необыкновенным лучами

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("H://input.txt"));
		FileOutputStream fos = new FileOutputStream("H://output.txt");
   	 	PrintStream out = new PrintStream(fos);
   	 	
   	 	int angleOfRotationStart = in.nextInt();
   	 	int angleOfRotationStop = in.nextInt();
   	 	double depolarizationLimit = in.nextDouble();
   	 	in.close();
   	 	
   	 	Plate[] ourSystem = new Plate[PLATES_COUNT];
   	 	for(Plate p : ourSystem) {
   	 		p = new Plate(angleOfRotationStart, angleOfRotationStop, depolarizationLimit);
   	 	}
   	 	
   	 	for(int w = WAVELENGTH_START; w <= WAVELENGTH_STOP; w += 5) {
   	 		double[][] result = Polarizer.POLARIZER_0_DEGREES;
   	 		
   	 		for(Plate p : ourSystem) {
   	 			double d = (Math.PI * DIFFERENCE * p.getThickness()) / w; //фазовый набег(?)
   	 			double b = Math.cos(d);
   	 			double m = Math.sin(d);
   	 			
   	 			double[][] mullerMatrix = {
   	 					{ 1, 0, 0, 0 },
   	 					{ 0, Math.pow(Math.cos(2*p.getAngle()),2)+Math.pow(Math.sin(2*p.getAngle()),2)*b, Math.pow(Math.cos(2*p.getAngle()),2)*Math.pow(Math.sin(2*p.getAngle()),2)*(1-b), -(Math.sin(2*p.getAngle())*m) },
   	                    { 0, Math.cos(2*p.getAngle())*Math.sin(2*p.getAngle())*(1-b), Math.pow(Math.sin(2*p.getAngle()),2)+Math.pow(Math.cos(2*p.getAngle()),2)*b, Math.cos(2*p.getAngle())*m },
   	                    { 0, Math.sin(2*p.getAngle())*m, -(Math.cos(2*p.getAngle())*m), b }
   	 			};
   	 			
   	 			double[][] temp1 = MatrixArithmetic.multiplicationMatrixNumber(mullerMatrix, 1 - p.getDepolarization());
   	 			double[][] temp2 = MatrixArithmetic.multiplicationMatrixNumber(Polarizer.DEPOLARIZER, p.getDepolarization());
   	 			temp1 = MatrixArithmetic.additionMatrixMatrix(temp1, temp2);
   	 			result = MatrixArithmetic.multiplicationMatrixMatrix(result, temp1);
   	 		}
   	 		
   	 		result = MatrixArithmetic.multiplicationMatrixMatrix(result, Polarizer.POLARIZER_90_DEGREES);
   	 		out.println(w +  "\t" +  result[0][0]);
   	 	}
   	 	
   	 	out.close();
	}

}
