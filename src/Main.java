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
	private static final int WAVELENGTH_STOP = 750;
	private static final double DIFFERENCE = 0.001; //разница между обыкновенным и необыкновенным лучами

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("H://input.txt"));
		int angleOfRotationStart = in.nextInt();
   	 	int angleOfRotationStop = in.nextInt();
   	 	double depolarizationLimit = in.nextDouble();
   	 	in.close();
   	 	
   	 	String outputFileName = "H://" + depolarizationLimit + ".txt";
		FileOutputStream fos = new FileOutputStream(outputFileName);
   	 	PrintStream out = new PrintStream(fos); 	
   	 	
   	 	Plate[] ourSystem = new Plate[PLATES_COUNT];
   	 	for(int i = 0; i < PLATES_COUNT; i++) {
   	 		//ourSystem[i] = new Plate(angleOfRotationStart, angleOfRotationStop, depolarizationLimit);
   	 		ourSystem[i] = new Plate(angleOfRotationStart, angleOfRotationStop, depolarizationLimit, 200);
   	 		System.out.println(i);
   	 		System.out.println(ourSystem[i].getAngle() / 0.017453);
   	 		System.out.println(ourSystem[i].getDepolarization());
   	 		System.out.println(ourSystem[i].getThickness());
   	 	}
   	 	
   	 	for(int w = WAVELENGTH_START; w <= WAVELENGTH_STOP; w += 5) {
   	 		double[][] result = Polarizer.POLARIZER_0_DEGREES;
   	 		
   	 		for(Plate p : ourSystem) {
   	 			double pA = p.getAngle();
   	 			int pT = p.getThickness();
   	 			double pD = p.getDepolarization();
   	 			
   	 			double d = (pT * DIFFERENCE * Math.PI) / w; //фазовый набег(?)
   	 			double b = Math.cos(d);
   	 			double m = Math.sin(d);
   	 			
   	 			double[][] mullerMatrix = {
   	 					{ 1, 0, 0, 0 },
   	 					{ 0, Math.pow(Math.cos(2*pA),2)+Math.pow(Math.sin(2*pA),2)*b, Math.pow(Math.cos(2*pA),2)*Math.pow(Math.sin(2*pA),2)*(1-b), -(Math.sin(2*pA)*m) },
   	                    { 0, Math.cos(2*pA)*Math.sin(2*pA)*(1-b), Math.pow(Math.sin(2*pA),2)+Math.pow(Math.cos(2*pA),2)*b, Math.cos(2*pA)*m },
   	                    { 0, Math.sin(2*pA)*m, -(Math.cos(2*pA)*m), b }
   	 			};
   	 			
   	 			double[][] temp1 = MatrixArithmetic.multiplicationMatrixNumber(mullerMatrix, 1 - pD);
   	 			double[][] temp2 = MatrixArithmetic.multiplicationMatrixNumber(Polarizer.DEPOLARIZER, pD);
   	 			temp1 = MatrixArithmetic.additionMatrixMatrix(temp1, temp2);
   	 			result = MatrixArithmetic.multiplicationMatrixMatrix(result, temp1);
   	 		}
   	 		
   	 		result = MatrixArithmetic.multiplicationMatrixMatrix(result, Polarizer.POLARIZER_90_DEGREES);
   	 		out.println(w +  "\t" +  result[0][0]);
   	 	}
   	 	
   	 	out.close();
	}

}
