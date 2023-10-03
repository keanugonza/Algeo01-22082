package src.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import src.Method.Invers;
import src.Method.Matrix;

public class InversMenu {
    private static Scanner scan = new Scanner(System.in);

    public static boolean fromFile() {
        System.out.println("         PILIHAN INPUT         ");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.println();
        System.out.println("Masukkan pilihan input (1/2): ");
        int fromFile = scan.nextInt();
        while ((fromFile != 1) && (fromFile != 2)){
            System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.");
            fromFile = scan.nextInt();
        }
        if (fromFile == 1) {
            return false;
        } else {
            return true;
        }
    }

    public static void menu() {
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
                    System.out.println("Input nama path file anda : ");    
                    String fileName = scan.nextLine();
                    try {
                        m = Matrix.fileToMatrix(fileName);
                    } catch (FileNotFoundException e) {
                        System.out.println("Tidak ditemukan file.");
                        inputValid = false;
                        break;
                    }
                } else {
                    m.inputSquareMatrix();
                }
                if (inputValid = true){
                    m = Invers.inversGaussJordan(m);
                }
                break;
            case 2:
                if (fromFile()){
                    System.out.println("Input nama path file anda : ");    
                    String fileName = scan.nextLine();
                    try {
                        m = Matrix.fileToMatrix(fileName);
                    } catch (FileNotFoundException e) {
                        System.out.println("Tidak ditemukan file.");
                        inputValid = false;
                        break;
                    }
                } else {
                    m.inputSquareMatrix();
                }
                if (inputValid = true){
                    m = Invers.inversGaussJordan(m);
                }
                break;
            default:
                inputValid = false;
                System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
                menu();
        }
        if (inputValid) {
            if (m != null) {
                System.out.println();
                System.out.println("Matriks balikan: ");
                m.displayMatrix();
                Matrix.saveMatrix(m);
            } else {
                String result = "Matriks tidak memiliki balikan";
                System.out.println();
                System.out.println(result);
                Matrix.saveString(result);
            }
        }
        
    }
}