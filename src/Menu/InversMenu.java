package src.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import src.Method.Invers;
import src.Method.Matrix;

public class InversMenu {
    private static Scanner scan = new Scanner(System.in);

    public static boolean fromFile() { // membaca masukan user dari keyboard atau file
        System.out.println("         PILIHAN INPUT         ");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.println();
        System.out.println("Masukkan pilihan input (1/2): ");
        int fromFile = scan.nextInt();
        while ((fromFile != 1) && (fromFile != 2)){ // menerima input hanya 1 atau 2 sampai benar 
            System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.");
            fromFile = scan.nextInt();
        }
        if (fromFile == 1) { // dari keyboard maka false
            return false;
        } else { // dari file maka true
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
            case 1: // menggunakan metode eliminasi gauss jordan
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
            case 2: // menggunakan metode adjoint
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
                inputValid = false; // jika input file salah salah maka input tidak valid sehingga program tidak dieksekusi
                System.out.println("Input tidak dikenali. Mohon hanya masukkan 1 atau 2.\n");
                menu(); // kembali ke menu atau meminta input sampai benar yaitu 1 atau 2
        }
        if (inputValid) {
            if (m != null) { // matriks memiliki balikan, return dari fungsi menghitung invers bukan null
                System.out.println();
                System.out.println("Matriks balikan: ");
                m.displayMatrix();
                Matrix.saveMatrix(m);
            } else { // matriks tidak memiliki balikan, return dari fungsi menghitung invers adalah null
                String result = "Matriks tidak memiliki balikan";
                System.out.println();
                System.out.println(result);
                Matrix.saveString(result);
            }
        }
        
    }
}