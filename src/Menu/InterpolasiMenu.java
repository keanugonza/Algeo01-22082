package src.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;
import src.Method.Matrix;
import java.lang.Math;

public class InterpolasiMenu {
    private static Scanner scan = new Scanner(System.in);

    public static void Interpolasi(){
        double x,y;
        int derajat;
        System.out.println("Masukkan derajat persamaan: ");
        derajat = scan.nextInt();
        int i,j,k = 0, count;
        i = derajat + 1;
        Matrix n = new Matrix(i,i+1);
        while (i > 0){
            count = derajat;
            System.out.println("Masukkan titik: ");
            x = scan.nextDouble();
            y = scan.nextDouble();
            for(j=0;j < n.col - 1;j++){
                n.m[k][j] = Math.pow(x, count);
                count -= 1;
            n.m[k][n.col-1] = y;
            }
            k++;
            i--;
        }
        System.out.println("Masukkan nilai x yang ingin diketahui: ");
        double p = scan.nextDouble();
        double hasil = 0;
        double[] l_x = new double[derajat+1];
        l_x = BicubicMenu.splGaussBicubic(n);
        for (i=0;i<derajat;i++){
            hasil += l_x[i]*Math.pow(p, derajat);
            derajat -= 1;
        }
        System.out.printf("Hasil dari f(%f) adalah %f",p,hasil);
    }

    public static void InterpolasiFile(Matrix n){
        double x,y;
        int derajat;
        derajat = n.row-2;
        int i,j,k = 0, count=0;
        i = derajat + 1;
        Matrix m1 = new Matrix(0, 0);
        while (i > 0){
            count = derajat;
            x = n.m[k][0];
            y = n.m[k][1];
            for(j=0;j < m1.col - 1;j++){
                m1.m[k][j] = Math.pow(x, count);
                count -= 1;
            m1.m[k][m1.col-1] = y;
            }
            k++;
            i--;
        }
        double p = n.m[n.row-1][0];
        double hasil = 0;
        double[] l_x = new double[derajat+1];
        l_x = BicubicMenu.splGaussBicubic(m1);
        for (i=0;i<derajat;i++){
            hasil += l_x[i]*Math.pow(p, derajat);
            derajat -= 1;
        }
        System.out.printf("Hasil dari f(%f) adalah %f",p,hasil);
    }

    public static void Interpolasimenu() throws FileNotFoundException{
        System.out.println();
        System.out.println("Interpolasi Polinomial");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.print("Masukkan pilihan input: ");
        int opt = 0;
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
                Interpolasi();
                break;
            case 2:
                Scanner scan = new Scanner(System.in);
                System.out.println("Input path file anda : ");    
                String fileName = scan.nextLine();
                inputMat = Matrix.fileToMatrix(fileName);

                break;
            default:
                inputValid = false;
                System.out.println("Input anda kurang tepat. Mohon masukkan 1 atau 2.\n");
                Interpolasimenu();
        }
        if (inputValid) {
        }
    }

    public static void main(String[] args){
        
    }
}