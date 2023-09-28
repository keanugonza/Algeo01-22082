package src.Method;

public class Cramer {
    public static String cramer(Matrix m1) {
        String result = new String();
        Matrix matrixMain = new Matrix(0, 0);
        Matrix matrixEq = new Matrix(0, 0);
        m1.splitMatrix(matrixMain, matrixEq, m1.col - 1); // memecah matrix augmented menjadi Main dan Eq
        if (matrixMain.isSquare()){ // kaidah cramer hanya berlaku untuk matrix pesegi
            if (Determinan.determinanKofaktor(matrixMain) == 0){ // determinan Main tidak boleh 0
                result = "SPL memiliki banyak solusi atau tidak memiliki solusi. Coba metode lain.\n";
            } else {
                Matrix matrixSubs = new Matrix(matrixMain.row, matrixMain.col);
                double determinan = Determinan.determinanKofaktor(matrixMain);
                double[] matrixResult = new double[m1.row];
                for ( int i = 0; i < matrixMain.col; i++){
                    matrixSubs.copyMatrix(matrixMain);
                    for( int j = 0; j < matrixMain.row; j++){ 
                        matrixSubs.m[j][i] = matrixEq.m[j][0];
                    }
                    matrixResult[i] = Determinan.determinanKofaktor(matrixSubs) / determinan;
                }
                for (int n = 0; n < m1.row; n++){
                    double varN = matrixResult[n];
                    result += ("x" + (n + 1) + " = " + String.format("%.4f", varN) + "\n");
                }
            }
        } else {
            result = "Kaidah cramer tidak berlaku untuk input. Coba metode lain.\n";
        }
        
        return result;
    }
}
