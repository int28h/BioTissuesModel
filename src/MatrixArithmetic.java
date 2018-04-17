//здесь арифметические операции над матрицами

public class MatrixArithmetic {
	public static double[][] multiplicationMatrixNumber(double[][] m, double n) {
		double[][] result = new double[4][4];		
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	result[i][j] = m[i][j] * n;
            }
        }		
		return result;
	}
	
	public static double[][] multiplicationMatrixMatrix(double[][] m1, double [][]m2) {
		double[][] result = new double[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				result[i][j] = 0;
				for (int k = 0; k < 4; k++) {
					result[i][j] += m1[i][k] * m2[k][j];
				}
			}
	    }
		return result;
	}
	
	public static double[][] additionMatrixMatrix(double[][] m1, double [][]m2) {
		double[][] result = new double[4][4];
		for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                    result[i][j] = m1[i][j] + m2[i][j];
                }
        }
		return result;
	}
}
