package src.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import src.Method.Determinan;
import src.Method.Matrix;

public class DeterminanMenu {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("=================== MENU DETRMINAN ===================");
        System.out.print("\n");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.print("Pilih input matrix (1/2): ");
        int input = scan.nextInt();
        Matrix m1 = new Matrix(0,0);
        switch (input){
            case 1:
                m1.inputRowCol();
                m1.readMatrix();
                System.out.print("\n");
                System.out.println("1. Metode kofaktor");
                System.out.println("2. Metode Gauss");
                System.out.print("Pilih metode yang akan digunakan (1/2): ");
                int metodeKeyboard = scan.nextInt();
                
                double hasilk;
                
                if (metodeKeyboard == 1){
                    hasilk = Determinan.determinanKofaktor(m1);
                }
                else{
                    hasilk = Determinan.determinanGauss(m1);
                }
                System.out.print("\n");
                System.out.println("Determinan dari matrix tersebut: " + hasilk);
                
                
            case 2:
                Scanner scan = new Scanner(System.in);
                System.out.print("\n");
                System.out.print("Masukan nama file:");
                String namaFile = scan.nextLine();
                m1 = Matrix.fileToMatrix(namaFile);
                System.out.print("\n");
                System.out.println("1. Metode kofaktor");
                System.out.println("2. Metode Gauss");
                System.out.print("Pilih metode yang akan digunakan (1/2): ");
                int metode = scan.nextInt();
                
                double hasilf;
                
                if (metode == 1){
                    hasilf = Determinan.determinanKofaktor(m1);
                }
                else{
                    hasilf = Determinan.determinanGauss(m1);
                }
                System.out.print("\n");
                System.out.print("Determinan dari matrix tersebut: " + hasilf);         
        }
        
    }
}
