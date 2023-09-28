package src.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import src.Method.Determinan;
import src.Method.Matrix;

public class RLBMenu {
    private static Scanner scan = new Scanner(System.in);

    public static void readKeyboard(Matrix input, Matrix x) {
        int n, m;
        System.out.print("Masukkan jumlah peubah : ");
        scan.nextLine();
        n = scan.nextInt();
        System.out.print("Masukkan jumlah sampel : ");
        scan.nextLine();
        m = scan.nextInt();
        scan.nextLine();
        System.out.println("Masukkan x1i, x2i,... xni, y: ");
        input.row = m;
        input.col = n + 1;
        input.m = new double[m][n + 1];
        int i = 0;
        input.readMatrix();
        x.row = 1;
        x.col = n;
        x.m = new double[1][n];
        for (i = 0; i < n; i++) {
            System.out.print("Masukkan nilai x" + (i + 1) + ": ");
            x.m[0][i] = scan.nextDouble();
        }
    }
    
    public static Matrix toRLB(Matrix m1) {
        Matrix matrixMain = new Matrix(m1.col, m1.col + 1);
        for (int i = 0; i < m1.col ; i++){
            for (int j = 0; j < m1.col + 1; j++){
                for (int k = 0; k < m1.row; k++){
                    if (i == 0){
                        if (j == 0){
                            matrixMain.m[i][j] += 1;
                        } else {
                            matrixMain.m[i][j] += m1.m[k][j-1];
                        }
                    } else {
                        if (j == 0){
                            matrixMain.m[i][j] += m1.m[k][i-1];
                        } else {
                            matrixMain.m[i][j] += m1.m[k][i-1] * m1.m[k][j-1];
                        }
                    }
                }
            }
        }
        return matrixMain;
    }

    public static void output1RLB(Matrix m1, Matrix x) {
        String result = new String();
        Matrix matrixMain = new Matrix(0, 0);
        Matrix matrixEq = new Matrix(0, 0);
        double estimationValue = 0;
        m1.splitMatrix(matrixMain, matrixEq, m1.col - 1); // memecah matrix augmented menjadi Main dan Eq
        if (matrixMain.isSquare()){ // kaidah cramer hanya berlaku untuk matrix pesegi
            if (Determinan.determinanKofaktor(matrixMain) == 0){ // determinan Main tidak boleh 0
                result = "SPL memiliki banyak solusi atau tidak memiliki solusi. Coba metode lain.\n";
            } else {
                Matrix matrixSubs = new Matrix(matrixMain.row, matrixMain.col);
                double determinan = Determinan.determinanKofaktor(matrixMain);
                double[] matrixResult = new double[m1.row];
                for (int i = 0; i < matrixMain.col; i++){
                    matrixSubs.copyMatrix(matrixMain);
                    for( int j = 0; j < matrixMain.row; j++){ 
                        matrixSubs.m[j][i] = matrixEq.m[j][0];
                    }
                    matrixResult[i] = Determinan.determinanKofaktor(matrixSubs) / determinan;
                }
                for (int n = 0; n < m1.row; n++){
                    double varN = matrixResult[n];
                    if (n == 0){
                        result += ("y = " + String.format("%.4f", varN));
                        if (x.col != 0){
                            estimationValue += varN;
                        }
                    } else {
                        result += (" + " + String.format("%.4f", varN) + "x" + (n));
                        if (x.col != 0){
                            estimationValue += varN;
                        }
                    }
                }
            }
        } else {
            result = "Kaidah cramer tidak berlaku untuk input. Coba metode lain.\n";
        }
        if (x.col != 0){
            System.out.println(result);
        }
        System.out.println("Hasil estimasi untuk input anda adalah " + estimationValue);
    }

    public static void output2RLB(Matrix m1) {
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
                for (int i = 0; i < matrixMain.col; i++){
                    matrixSubs.copyMatrix(matrixMain);
                    for( int j = 0; j < matrixMain.row; j++){ 
                        matrixSubs.m[j][i] = matrixEq.m[j][0];
                    }
                    matrixResult[i] = Determinan.determinanKofaktor(matrixSubs) / determinan;
                }
                for (int n = 0; n < m1.row; n++){
                    double varN = matrixResult[n];
                    if (n == 0){
                        result += ("y = " + String.format("%.4f", varN));
                    } else {
                        result += (" + " + String.format("%.4f", varN) + "x" + (n));
                    }
                }
            }
        } else {
            result = "Kaidah cramer tidak berlaku untuk input. Coba metode lain.\n";
        }
        System.out.println(result);
    }


    public static void menu() throws FileNotFoundException {
        System.out.println();
        System.out.println("REGRESI LINEAR BERGANDA");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.print("Masukkan pilihan input: ");
        int opt = 0;
        boolean inputValid = true;
        Matrix inputMat = new Matrix(0, 0);
        Matrix x = new Matrix(0, 0);
        try {
            opt = scan.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        switch (opt) {
            case 1:
                readKeyboard(inputMat, x);
                break;
            case 2:
                Scanner scan = new Scanner(System.in);
                System.out.println("input nama file anda : ");    
                String fileName = scan.nextLine();
                inputMat = Matrix.fileToMatrix(fileName);
                break;
            default:
                inputValid = false;
                System.out.println("Input anda kurang tepat. Mohon masukkan 1 atau 2.\n");
        }
        if (inputValid) {
            inputMat = toRLB(inputMat);
            if (opt == 1){
                output1RLB(inputMat, x);
            } else if (opt == 2){
                output2RLB(inputMat);
            }
        }
    }
}
