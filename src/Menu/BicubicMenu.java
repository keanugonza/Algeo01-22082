package src.Menu;

import java.util.Scanner;
import java.lang.Math;

public class BicubicMenu {
    private static Scanner scan = new Scanner(System.in);

    private static double[] fungsi1(int x, int y){
        double[] l = new double[16];
        int c,i = 0,j = 0;
        for(c=0;c<16;c++){
            l[c] = Math.pow(x, i)*Math.pow(y, j);
            j++;
            if (j > 3){
                j = 0;
                i++;
            }
            if (i > 3){
                i = 0;
            }
        }
        return l;
    }

    private static double[] fungsiX(int x, int y){
        double[] l = new double[16];
        int c,i = 1,j = 0;
        for(c=0;c<16;c++){
            l[c] = Math.pow(x, i-1)*Math.pow(y, j)*i;
            j++;
            if (j > 3){
                j = 0;
                i++;
            }
            if (i > 3){
                i = 1;
            }
        }
        return l;
    }

    private static double[] fungsiY(int x, int y){
        double[] l = new double[16];
        int c,i = 0,j = 1;
        for(c=0;c<16;c++){
            l[c] = Math.pow(x, i)*Math.pow(y, j-1)*j;
            j++;
            if (j > 3){
                j = 1;
                i++;
            }
            if (i > 3){
                i = 0;
            }
        }
        return l;
    }

    private static double[] fungsiXY(int x, int y){
        double[] l = new double[16];
        int c,i = 1,j = 01;
        for(c=0;c<16;c++){
            l[c] = Math.pow(x, i-1)*Math.pow(y, j-1)*i*j;
            j++;
            if (j > 3){
                j = 1;
                i++;
            }
            if (i > 3){
                i = 1;
            }
        }
        return l;
    }

    public static Matrix bikubik(){
        int i, x=0, y=0;
        double a, b;
        Matrix m1 = new Matrix(4, 4);
        Matrix f = new Matrix(16, 16);
        System.out.println("Masukkan Matrix: ");
        m1.readMatrix();
        System.out.println("Masukkan nilai x dan y yang ingin diketahui: ");
        a = scan.nextDouble();
        b = scan.nextDouble();

        for (i=0; i<16; i++){
            if (i < 4){
                m1.m[i] = fungsi1(x, y);
            } else if (4 < i && i < 8){
                m1.m[i] = fungsiX(x, y);
            } else if (8 < i && i < 12){
                m1.m[i] = fungsiY(x, y);
            } else {
                m1.m[i] = fungsiXY(x, y);
            }
            y++;
            if (y>1){
                y = 0;
                x++;
            }
            if (x>1){
                x = 0;
            }
        }
        SPLMenu.splGauss(m1);
        return m1;
    }
}
