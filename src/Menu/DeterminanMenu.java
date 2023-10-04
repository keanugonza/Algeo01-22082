package src.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import src.Method.Determinan;
import src.Method.Matrix;

public class DeterminanMenu {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("=================== MENU DETRMINAN ===================");
        System.out.print("\n");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.print("Pilih input matrix (1/2): ");
        int input = scan.nextInt();
        while (input < 1 || input > 2){
            System.out.print("Mohon masukan angka 1/2 saja:");
            input = scan.nextInt();
        }
        Matrix m1 = new Matrix(0,0);
        switch (input){
            case 1:  //masukan dar keyboard
                System.out.print("\n");
                m1.inputRowCol();
                m1.readMatrix();
                System.out.print("\n");
                System.out.println("1. Metode kofaktor");
                System.out.println("2. Metode Gauss");
                System.out.print("Pilih metode yang akan digunakan (1/2): ");
                int metodeKeyboard = scan.nextInt();
                while (metodeKeyboard < 1 || metodeKeyboard > 2){
                    System.out.print("Mohon masukan angka 1/2 saja: ");
                    metodeKeyboard = scan.nextInt();
                }
                double hasilk;
                
                if (metodeKeyboard == 1){
                    hasilk = Determinan.determinanKofaktor(m1);
                }
                else{
                    hasilk = Determinan.determinanGauss(m1);
                }
                System.out.print("\n");
                System.out.println("Determinan dari matrix tersebut: " + hasilk);

                System.out.print("\n");
                String s = "Hasil determinannya: ";
                s = s+ (String.format("%.4f", hasilk));
                Matrix.saveString(s);
                break;
                
            case 2: //masukan dari file
                System.out.print("\n");
                System.out.print("Masukan nama path file:");
                String namaFile = scan.next();
                try{
                    m1 = Matrix.fileToMatrix(namaFile);
                    System.out.print("\n");
                    System.out.println("1. Metode kofaktor");
                    System.out.println("2. Metode Gauss");
                    System.out.print("Pilih metode yang akan digunakan (1/2): ");
                    int metode =0;
                    metode = scan.nextInt();
                    while (metode < 1 || metode > 2){
                        System.out.print("Mohon masukan angka 1/2 saja: ");
                        metode = scan.nextInt();
                    }
                    double hasilf;
                    
                    if (metode == 1){
                        hasilf = Determinan.determinanKofaktor(m1);
                    }
                    else{
                        hasilf = Determinan.determinanGauss(m1);
                    }
                    System.out.print("\n");
                    System.out.print("Determinan dari matrix tersebut: " + hasilf);

                    System.out.print("\n");
                    String s1 = "Hasil determinannya: ";
                    s1 = s1+ (String.format("%.4f", hasilf));
                    Matrix.saveString(s1);

                } catch (FileNotFoundException e) {
                    System.out.println("Tidak ditemukan file.");
                }
                break;         
        }

        
    }
}
