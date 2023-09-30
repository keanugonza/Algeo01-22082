package src.Menu;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.Method.Gauss_jordan;
import src.Method.Invers;
import src.Method.Matrix;
import src.Method.Cramer;
import src.Method.Gauss;

public class SPLMenu {
    private static Scanner scan = new Scanner(System.in);

    public static boolean checkMatrix(Matrix n){
        int i = n.row-1, j=0, count = 0;
        boolean found = false;
        while (found == false){
            if (Gauss.rounding(n.m[i][j]) == 0){
                count++;
            }
            j++;
            if (j > n.col - 1){
                j = 0;
                i -= 1;
                if(count == n.col-1){
                    found = true;
                }
                count = 0;
            }
        }
        return found;
    }

    public static int zeroRow(Matrix n){
        int i = n.row-1,j = 0, count = 0;
        boolean found = false;
        while (found == false && i > 0){
            if (n.m[i][j] != 0){
                found = true;
            }
            j += 1;
            if (j > n.col - 1){
                j = 0;
                count += 1;
                i -= 1;
            }
        }
        return count;
    }

    public static int[] makelist(Matrix n, int row){
        int i, count = getOther(n.m[row]), j=0;
        int[] l = new int[count];
        for (i = 0; i < n.col-1; i++){
            if (n.m[row][i] != 0){
                l[j] = i;
                j += 1;
            }
        }
        return l;
    }

    public static int getOther(double[] l){
        int i, count = 0;
        for (i = 0; i < l.length - 1; i++){
            if (l[i] != 0){
                count += 1;
            }
        }
        return count - 1;
    }

    public static void splGauss(Matrix m1){
        int i,j, r_zero = 0, count = 1;
        boolean ada = false;
        Gauss.eliminasiGauss(m1, true);
        if (checkMatrix(m1) == true){
            System.out.println("Persamaan tidak memiliki penyelesaian.");
        } else {
            m1.displayMatrix();
            r_zero = zeroRow(m1);
            for (i = 0; i < m1.row - r_zero; i++){
                for (j = i+1; j < m1.row - r_zero; j++){
                    Gauss.add(m1, i, j, -m1.m[i][Gauss_jordan.getFirstOne(m1,j)]);
                }
            }
            for (i = 0; i < m1.row - r_zero; i++){
                if (r_zero == 0){
                    System.out.printf("x%d = %f", i + 1, m1.m[i][m1.col-1]);
                    System.out.println();
                } else {
                    int x;
                    x = Gauss_jordan.getFirstOne(m1, i);
                    if (m1.m[i][m1.col-1] != 0){
                        System.out.printf("x%d = %f", x + 1, m1.m[i][m1.col-1]);
                    } else {
                        System.out.printf("x%d = ", x + 1);
                        ada = true;
                    }
                    if (getOther(m1.m[i]) > 0){
                        count = Gauss_jordan.getFirstOne(m1,i) + 1;
                        while (count < m1.col - 1){
                            if (m1.m[i][count] != 0){
                                if (ada == false){
                                    if (m1.m[i][count] < 0){
                                        System.out.printf(" + ");
                                    } else {
                                        System.out.printf(" - ");
                                    }
                                }
                                System.out.printf("%fx%d", m1.m[i][count],count + 1);
                            }
                            count += 1;
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void splGaussJordan(Matrix m1){
        int i, r_zero = 0, count = 1;
        boolean ada = false;
        Gauss.eliminasiGauss(m1, true);
        if (checkMatrix(m1) == true){
            System.out.println("Persamaan tidak memiliki penyelesaian.");
        } else {
            r_zero = zeroRow(m1);
            for (i = 0; i < m1.row - r_zero; i++){
                if (r_zero == 0){
                    System.out.printf("x%d = %f", i + 1, m1.m[i][m1.col-1]);
                    System.out.println();
                } else {
                    int x;
                    x = Gauss_jordan.getFirstOne(m1, i);
                    if (m1.m[i][m1.col-1] != 0){
                        System.out.printf("x%d = %f", x + 1, m1.m[i][m1.col-1]);
                    } else {
                        System.out.printf("x%d = ", x + 1);
                        ada = true;
                    }
                    if (getOther(m1.m[i]) > 0){
                        count = Gauss_jordan.getFirstOne(m1,i) + 1;
                        while (count < m1.col - 1){
                            if (m1.m[i][count] != 0){
                                if (ada == false){
                                    if (m1.m[i][count] < 0){
                                        System.out.printf(" + ");
                                    } else {
                                        System.out.printf(" - ");
                                    }
                                }
                                System.out.printf("%fx%d", -m1.m[i][count],count + 1);
                            }
                            count += 1;
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void splInversBalikan(Matrix m1){
        int j;
        String result = new String();
        Matrix hasil = new Matrix(0, 0);
        Matrix matrixMain = new Matrix(0, 0);
        Matrix matrixEq = new Matrix(0, 0);
        m1.splitMatrix(matrixMain, matrixEq, m1.col - 1);
        matrixMain = Invers.inversAdjoint(matrixMain);
        if (matrixMain != null) {
            System.out.println();
            System.out.println("Matriks balikan: ");
            matrixMain.displayMatrix();
            hasil = hasil.multiplyMatrix(matrixMain, matrixEq);
            for (j = 0; j < hasil.row; j++){
                result += ("x" + (j + 1) + " = " + String.format("%.4f", hasil.m[j][0]) + "\n");
            }
            System.out.println(result);
            Matrix.saveString(result);
        } else {
            result = "Matriks tidak memiliki balikan sehingga tidak bisa diselesaikan. Coba metoed lain";
            System.out.println();
            System.out.println(result);
            Matrix.saveString(result);
        }
        
    }

    public static void splCramer(Matrix m1){
        String result = new String();
        result = Cramer.cramer(m1);
        System.out.println(result);
        Matrix.saveString(result);
    }

    public static void menu() throws FileNotFoundException {
        System.out.println();
        System.out.println("Sistem Persamaan Linear");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.print("Masukkan pilihan input: ");
        int opt = 0;
        int method;
        boolean inputValid = true;
        Matrix inputMat = new Matrix(0, 0);
        try {
            opt = scan.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        switch(opt) {
            case 1:
                inputMat.inputRowCol();
                inputMat.readMatrix();
                break;
            case 2:
                Scanner scan = new Scanner(System.in);
                System.out.println("Input nama path file anda : ");    
                String fileName = scan.nextLine();
                inputMat = Matrix.fileToMatrix(fileName);
                break;
            default:
                inputValid = false;
                System.out.println("Input anda kurang tepat. Mohon masukkan 1 atau 2.\n");
                menu();
        }
        if (inputValid) {
            System.out.println("""
        Pilih metode penyelesaian:
        1. Metode eliminasi Gauss
        2. Metode eliminasi Gauss-Jordan
        3. Metode matriks balikan
        4. Kaidah Cramer
        """);
        method = scan.nextInt();
        while (method != 1 && method != 2 && method != 3 && method != 4){
            System.out.print("Mohon masukan angka 1, 2, 3, atau 4");
            method = scan.nextInt();
        }
        switch (method){
            case 1:
                splGauss(inputMat);
                break;
            case 2:
                splGaussJordan(inputMat);
                break;
            case 3:
                splInversBalikan(inputMat);
                break;
            case 4:
                splCramer(inputMat);
                break;
        }
        }
    }
}