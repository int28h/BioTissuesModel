import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Scanner;

import Components.Plate;
import Components.Polarizer;

public class Main {	
	private static final int PLATES_COUNT = 10;
	private static final int WAVELENGTH_START = 100;
	private static final int WAVELENGTH_STOP = 900;
	private static final double DIFFERENCE = 0.001; //разница между обыкновенным и необыкновенным лучами

	public static void main(String[] args) throws FileNotFoundException {
		boolean isThicknessWithFlucuations = true;
		
		Scanner in = new Scanner(System.in);
		int angleOfRotation = in.nextInt();
		int differenceForAngles = in.nextInt();
		int thickness = in.nextInt();
   	 	in.close();
   	 	
		FileOutputStream fos = new FileOutputStream("H:\\Result.txt");
   	 	PrintStream out = new PrintStream(fos); 
   	 	
   	 	//FileOutputStream los = new FileOutputStream("H:\\Logs.txt");
   	 	//PrintStream logs = new PrintStream(los); 
   	 	
   	 	Plate[] ourSystem = new Plate[PLATES_COUNT];
   	 	for(int i = 0; i < PLATES_COUNT; i++) {   	 		
   	 		if(i % 2 == 0) {
   	 			ourSystem[i] = new Plate(angleOfRotation, differenceForAngles, thickness, isThicknessWithFlucuations);
   	 		} else {
   	 			ourSystem[i] = new Plate(-angleOfRotation, differenceForAngles, thickness, isThicknessWithFlucuations);
   	 		}
   	 		System.out.println(i);
   	 		System.out.println(ourSystem[i].getAngle() / 0.017453);
   	 		System.out.println(ourSystem[i].getThickness());
   	 	}
   	 	
   	 	for(int w = WAVELENGTH_START; w <= WAVELENGTH_STOP; w++) {
   	 		//logs.println("Длина волны "+ w);
   	 		double[][] result = Polarizer.POLARIZER_0_DEGREES;   	 		
   	 		//int numberOfPlate = 1;
   	 		
   	 		for(Plate p : ourSystem) {
   	 			double pA = p.getAngle();
   	 			int pT = p.getThickness();
   	 			
   	 			double d = (pT * DIFFERENCE * Math.PI) / w; //фазовый набег(?)
   	 			double b = Math.cos(d);
   	 			double m = Math.sin(d);
   	 			
   	 			double[][] mullerMatrix = {
   	 					{ 1, 0, 0, 0 },
   	 					{ 0, Math.pow(Math.cos(2*pA),2)+Math.pow(Math.sin(2*pA),2)*b, Math.pow(Math.cos(2*pA),2)*Math.pow(Math.sin(2*pA),2)*(1-b), -(Math.sin(2*pA)*m) },
   	                    { 0, Math.cos(2*pA)*Math.sin(2*pA)*(1-b), Math.pow(Math.sin(2*pA),2)+Math.pow(Math.cos(2*pA),2)*b, Math.cos(2*pA)*m },
   	                    { 0, Math.sin(2*pA)*m, -(Math.cos(2*pA)*m), b }
   	 			};
   	 			
   	 			result = MatrixArithmetic.multiplicationMatrixMatrix(result, mullerMatrix);   	 			
   	 			 	 			
   	 			/*logs.println("После прохождения пластины номер " + numberOfPlate);
   	 			for(int i = 0; i < 4; i++){
   	 				logs.print(result[i][0] + " ");
   	 				logs.print(result[i][1] + " ");
   	 				logs.print(result[i][2] + " ");
   	 				logs.println(result[i][3]);
   	 			}*/
   	 			//numberOfPlate++;
   	 		}
   	 		
   	 		result = MatrixArithmetic.multiplicationMatrixMatrix(result, Polarizer.POLARIZER_90_DEGREES);
   	 		
   	 		double gWithoutPi = 2 * DIFFERENCE * thickness * 1000 / w; //thickness - в мкм!
   	 		StringBuilder formattedG = new StringBuilder();
   	 		formattedG.append(gWithoutPi).toString().replaceAll(",", ".");
   	 		
   	 		String formattedRes = new DecimalFormat("#0.000000000").format(result[0][0]).replaceAll(",", ".");
   	 		
   	 		/*logs.println("Итог:");
   	 		for(int i = 0; i < 4; i++){
				logs.print(result[i][0] + " ");
				logs.print(result[i][1] + " ");
				logs.print(result[i][2] + " ");
				logs.println(result[i][3]);
			}*/
   	 		
   	 		out.println(formattedG.toString() +  "\t" +  formattedRes);
   	 		System.out.println(formattedG.toString() +  "\t" +  formattedRes);
   	 	}
   	 	
   	 	out.close();
   	 	//logs.close();
	}

}
