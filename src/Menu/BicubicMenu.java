package src.Menu;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Math;

import src.Method.Gauss;
import src.Method.Gauss_jordan;
import src.Method.Matrix;

public class BicubicMenu {
    private static Scanner scan = new Scanner(System.in);

    //membuat fungsi(x,y)
    private static double[] fungsi1(double x, double y){
        double[] l = new double[16];
        int c,i = 0,j = 0;
        for(c=0;c<16;c++){
            l[c] = Math.pow(x, i)*Math.pow(y, j);
            i++;
            if (i > 3){
                i = 0;
                j++;
            }
        }
        return l;
    }

    //membuat turunan x fungsi(x,y)
    private static double[] fungsiX(double x, double y){
        double[] l = new double[16];
        int c,i = 0,j = 0;
        for(c=0;c<16;c++){
            if(i==0){
                l[c] =0;
            }
            else{
                l[c] = Math.pow(x, i-1) * Math.pow(y, j)* i;
            }
            i++;
            if (i > 3){
                i = 0;
                j++;
            }
        }
        return l;
    }
    
    //membuat turunan y dari fungsi f(x,y)
    private static double[] fungsiY(double x, double y){
        double[] l = new double[16];
        int c,i = 0,j = 0;
        for(c=0;c<16;c++){
            if (j==0){
                l[c] =0;
            }
            else{
                l[c] = Math.pow(x, i)*Math.pow(y, j-1)*j;
            }
            i++;
            if (i > 3){
                i = 0;
                j++;
            }
        }
        return l;
    }

    //membuat turunan xydari fungsi f(x,y)
    private static double[] fungsiXY(double x, double y){
        double[] l = new double[16];
        int c,i = 0,j = 0;
        for(c=0;c<16;c++){
            if (i==0 || j==0){
                l[c] = 0;
            }
            else{
                l[c] = Math.pow(x, i-1) * Math.pow(y, j-1) * i * j;
            }
            i++;
            if (i > 3){
                i = 0;
                j++;
            }
        }
        return l;
    }


    //melakukan metode SPL pada matrix bicubic 16x17
    public static double[] splGaussBicubic(Matrix m1){
        int i,j, r_zero = 0;
        double[] l = new double[m1.row];
        Gauss.eliminasiGauss(m1, true);
        for (i = 0; i < m1.row - r_zero; i++){
            for (j = i+1; j < m1.row - r_zero; j++){
                Gauss.add(m1, i, j, -m1.m[i][Gauss_jordan.getFirstOne(m1,j)]);
            }
        }
        for(i = 0;i<m1.row;i++){
            l[i] = m1.m[i][m1.col-1];
        }
        return l;
    }


    //program utama bicubic
    public static void bikubik(){

        System.out.println("INTERPOLASI BICUBIC SPLINE");
        boolean file = true;
        int i, x=0, y=0;
        double a, b, hasil = 0;
        Matrix fromFile = new Matrix(0, 0);
        Matrix f = new Matrix(16, 16);
        System.out.print("Masukan nama path file: ");
        String namaFile = scan.next();
        try {
            fromFile = Matrix.fileToMatrix(namaFile);
        } catch (FileNotFoundException e) {
            System.out.println("Tidak ditemukan file.");
            file = false;
        }


        if (file){
        a = fromFile.m[4][0];
        b = fromFile.m[4][1];

        //membuat matrix 16x16
        for (i=0; i<16; i++){
            if (i < 4){
                f.m[i] = fungsi1(x, y);
            } else if (4 <= i && i < 8){
                f.m[i] = fungsiX(x, y);
            } else if (8 <= i && i < 12){
                f.m[i] = fungsiY(x, y);
            } else {
                f.m[i] = fungsiXY(x, y);
            }
            x++;
            if (x>1){
                x = 0;
                y++;
            }
            if (y>1){
                y = 0;
            }
        }

        //input ddari file
        Matrix tambahan = new Matrix(16,1);
        int j,p = 0;
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                tambahan.m[p][0] = fromFile.m[i][j];
                p++;
            }
        }

        Matrix M_final = new Matrix(0, 0);
        double[] l_nilaiA = new double[16], l_final = new double[16];

        M_final = Matrix.mergeMatrix(f, tambahan);      //memasukan input dari file ke kolom ke 17.
        l_nilaiA = splGaussBicubic(M_final);            //melakakn metode spl ke matrix 16x17
        l_final = fungsi1(a, b);

        for(i=0;i<16;i++){
            hasil += l_nilaiA[i]*l_final[i];
        }

        String s1 = "f(";
        s1 = s1 + (String.format("%2f", a)) + "," + (String.format("%2f", b)) + ") = " + (String.format("%4f", hasil));
        System.out.print("\n");
        System.out.println(s1);
        System.out.print("\n");
        Matrix.saveString(s1);
        System.out.print("\n");
        }
    }
}
