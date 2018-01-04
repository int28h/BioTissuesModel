
public class Polarizer {
	public static final double[][] POLARIZER_0_DEGREES = {
			{ 0.5, 0.5, 0, 0 },
	        { 0.5, 0.5, 0, 0 },
	        { 0, 0, 0, 0 },
	        { 0, 0, 0, 0 }	
	};
	
	public static final double[][] POLARIZER_90_DEGREES = {
			{ 0.5, -0.5, 0, 0 },
	        { -0.5, 0.5, 0, 0 },
	        { 0, 0, 0, 0 },
	        { 0, 0, 0, 0 }
	};
	
	public static final double[][] DEPOLARIZER = {
			{1, 0, 0, 0},
	        {0, 0, 0, 0},
	        {0, 0, 0, 0},
	        {0, 0, 0, 0}
	};
}
