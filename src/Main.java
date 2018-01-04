import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {	
	private final int PLATES_COUNT = 10;
	private final int WAVELENGTH_START = 400;
	private final int WAVELENGTH_STOP = 400;
	private final double DIFFERENCE = 0.001; //фазовый набег
	
	private static int angleOfRotationStart;
	private static int angleOfRotationStop;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input.txt"));
		FileOutputStream fos = new FileOutputStream("output.txt");
   	 	PrintStream out = new PrintStream(fos);
   	 	
   	 	angleOfRotationStart = in.nextInt();
   	 	angleOfRotationStop = in.nextInt();
	}

}
