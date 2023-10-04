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
        System.out.println("Masukkan titik: ");
        while (i > 0){
            count = derajat;
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
        String persamaan = "y = ";
        double hasil = 0;
        int derajat2 = derajat;
        double[] l_x = new double[derajat+1];
        l_x = BicubicMenu.splGaussBicubic(n);
        for (i=0;i<l_x.length;i++){
            if (l_x[i] != 0){
                if (i > 0){
                    if (l_x[i] < 0){
                        persamaan += " - ";
                    } else {
                        persamaan += " + ";
                    }
                }
                if (derajat == 0){
                    persamaan += String.format("%.4f",l_x[i]);
                } else {
                    if (l_x[i] == 1){
                        persamaan += "x^" + derajat;
                    } else {
                        persamaan += String.format("%.4fx^%d",l_x[i], derajat);
                    }
                }
            }
            derajat -= 1;
        }
        System.out.println("Persamaan hasi interpolasi: ");
        System.out.println(persamaan);
        System.out.println("Masukkan nilai x yang ingin diketahui: ");
        double p = scan.nextDouble();
        for (i=0;i<l_x.length;i++){
            hasil += l_x[i]*Math.pow(p, derajat2);
            derajat2 -= 1;
        }
        System.out.printf("Hasil dari f(%f) adalah %.4f",p,hasil);
        Matrix.saveString(persamaan);
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
        String persamaan = "y = ";
        double hasil = 0;
        int derajat2 = derajat;
        double[] l_x = new double[derajat+1];
        l_x = BicubicMenu.splGaussBicubic(n);
        for (i=0;i<l_x.length;i++){
            if (l_x[i] != 0){
                if (i > 0){
                    if (l_x[i] < 0){
                        persamaan += " - ";
                    } else {
                        persamaan += " + ";
                    }
                }
                if (derajat == 0){
                    persamaan += String.format("%.4f",l_x[i]);
                } else {
                    if (l_x[i] == 1){
                        persamaan += "x^" + derajat;
                    } else {
                        persamaan += String.format("%.4fx^%d",l_x[i], derajat);
                    }
                }
            }
            derajat -= 1;
        }
        System.out.println("Persamaan hasi interpolasi: ");
        System.out.println(persamaan);
        System.out.println("Masukkan nilai x yang ingin diketahui: ");
        double p = scan.nextDouble();
        for (i=0;i<l_x.length;i++){
            hasil += l_x[i]*Math.pow(p, derajat2);
            derajat2 -= 1;
        }
        System.out.printf("Hasil dari f(%f) adalah %.4f",p,hasil);
        Matrix.saveString(persamaan);
    }

    public static void Interpolasimenu() throws FileNotFoundException{
        System.out.println();
        System.out.println("Interpolasi Polinomial");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.print("Masukkan pilihan input: ");
        int opt = 0;
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
                InterpolasiFile(inputMat);
                break;
            default:
                System.out.println("Input anda kurang tepat. Mohon masukkan 1 atau 2.\n");
                Interpolasimenu();
        }
        
    }

    public static void main(String[] args) throws FileNotFoundException{
        Interpolasimenu();
    }
}