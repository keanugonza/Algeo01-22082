package src.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import src.Method.Invers;
import src.Method.Matrix;
// import Utils.Utils;

public class InversMenu {
    private static Scanner scan = new Scanner(System.in);

    public static boolean fromFile() {
        System.out.println("         PILIHAN INPUT         ");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.println();
        System.out.println("Masukkan pilihan input (1/2): ");
        int fromFile = scan.nextInt();
        System.out.println();
        if (fromFile == 1) {
            return false;
        }
        return true;
    }

    public static void menu() throws FileNotFoundException {
        System.out.println("         Matriks Balikan        ");
        System.out.println("1. Metode Eliminasi Gauss Jordan");
        System.out.println("2. Metode Adjoint");
        System.out.println("Masukkan pilihan metode (1/2): ");
        int opt = 0;
        boolean inputValid = true;
        try {
            opt = scan.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Matrix m = new Matrix(0, 0);
        switch (opt) {
            case 1:
                if (fromFile()){
                    System.out.print("Masukan nama file:");
                    String namaFile = scan.nextLine();
                    m = Matrix.fileToMatrix(namaFile);
                } else {
                    m.inputSquareMatrix();
                }
                m = Invers.inversGaussJordan(m);
                break;
            case 2:
                if (fromFile()){
                    System.out.print("Masukan nama file:");
                    String namaFile = scan.nextLine();
                    m = Matrix.fileToMatrix(namaFile);
                } else {
                    m.inputSquareMatrix();
                }
                m = Invers.inversAdjoint(m);
                break;
            default:
            inputValid = false;
            System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
        }
        if (inputValid) {
            if (m != null) {
                System.out.println();
                System.out.println("Matriks balikan: ");
                m.displayMatrix();
                // Utils.matrixToFile(m);
            } else {
                String result = "Matriks tidak memiliki balikan";
                System.out.println();
                System.out.println(result);
                // Utils.stringToFile(result);
            }
        }
        
    }
}