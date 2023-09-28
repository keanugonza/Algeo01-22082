package src.App;

import java.util.Scanner;
import java.lang.Math;

public class InterpolasiMenu {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        int derajat,i,j,k = 0, count;
        double x,y;
        System.out.println("Masukkan derajat persamaan: ");
        derajat = scan.nextInt();
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
        SPLMenu.splGauss(n);
    }
}