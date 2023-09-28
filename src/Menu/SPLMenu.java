package src.Menu;
import java.util.Scanner;

import src.Method.Gauss_jordan;
import src.Method.Matrix;
import src.Method.Gauss;

public class SPLMenu {
    private static Scanner scan = new Scanner(System.in);

    public static int zeroRow(Matrix n){
        int i = n.row-1,j = 0, count = 0;
        boolean found = false;
        while (found == false && i > 0){
            if (n.m[i][j] != 0){
                found = true;
            }
            j += 1;
            if (j > n.col - 1){
                j = 0;
                count += 1;
                i -= 1;
            }
        }
        return count;
    }

    public static int[] makelist(Matrix n, int row){
        int i, count = getOther(n.m[row]), j=0;
        int[] l = new int[count];
        for (i = 0; i < n.col-1; i++){
            if (n.m[row][i] != 0){
                l[j] = i;
                j += 1;
            }
        }
        return l;
    }

    public static int getOther(double[] l){
        int i, count = 0;
        for (i = 0; i < l.length - 1; i++){
            if (l[i] != 0){
                count += 1;
            }
        }
        return count - 1;
    }

    public static void splGauss(Matrix m1){
        int i,j, r_zero = 0, count = 1;
        boolean ada = false;
        Gauss.eliminasiGauss(m1, true);
        r_zero = zeroRow(m1);
        for (i = 0; i < m1.row - r_zero; i++){
            for (j = i+1; j < m1.row - r_zero; j++){
                Gauss.add(m1, i, j, -m1.m[i][Gauss_jordan.getFirstOne(m1,j)]);
            }
        }
        for (i = 0; i < m1.row - r_zero; i++){
            if (r_zero == 0){
                System.out.printf("x%d = %f", i + 1, m1.m[i][m1.col-1]);
                System.out.println();
            } else {
                int x;
                x = Gauss_jordan.getFirstOne(m1, i);
                if (m1.m[i][m1.col-1] != 0){
                    System.out.printf("x%d = %f", x + 1, m1.m[i][m1.col-1]);
                } else {
                    System.out.printf("x%d = ", x + 1);
                    ada = true;
                }
                if (getOther(m1.m[i]) > 0){
                    count = Gauss_jordan.getFirstOne(m1,i) + 1;
                    while (count < m1.col - 1){
                        if (m1.m[i][count] != 0){
                            if (ada == false){
                                if (m1.m[i][count] < 0){
                                    System.out.printf(" + ");
                                } else {
                                    System.out.printf(" - ");
                                }
                            }
                            System.out.printf("%fx%d", -m1.m[i][count],count + 1);
                        }
                        count += 1;
                    }
                }
                System.out.println();
            }
        }
    }

    public static void splGaussJordan(Matrix m1){
        int i, r_zero = 0, count = 1;
        boolean ada = false;
        Gauss.eliminasiGauss(m1, true);
        r_zero = zeroRow(m1);
        for (i = 0; i < m1.row - r_zero; i++){
            if (r_zero == 0){
                System.out.printf("x%d = %f", i + 1, m1.m[i][m1.col-1]);
                System.out.println();
            } else {
                int x;
                x = Gauss_jordan.getFirstOne(m1, i);
                if (m1.m[i][m1.col-1] != 0){
                    System.out.printf("x%d = %f", x + 1, m1.m[i][m1.col-1]);
                } else {
                    System.out.printf("x%d = ", x + 1);
                    ada = true;
                }
                if (getOther(m1.m[i]) > 0){
                    count = Gauss_jordan.getFirstOne(m1,i) + 1;
                    while (count < m1.col - 1){
                        if (m1.m[i][count] != 0){
                            if (ada == false){
                                if (m1.m[i][count] < 0){
                                    System.out.printf(" + ");
                                } else {
                                    System.out.printf(" - ");
                                }
                            }
                            System.out.printf("%fx%d", -m1.m[i][count],count + 1);
                        }
                        count += 1;
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args){
        Matrix m1 = new Matrix(0, 0);
        int method, masukan;
        System.out.println("""
        Pilih jenis masukan:    
        1. Keyboard
        2. File
        """
        );
        masukan = scan.nextInt();
        if (masukan == 1){
            System.out.println("Masukkan matriks persamaan: ");
            m1.inputRowCol();
            m1.readMatrix();
        } else {}
        System.out.println("Pilih metode penyelesaian: ");
        method = scan.nextInt();
        if (method == 1){
            splGauss(m1);
        } else if (method == 2){
            splGaussJordan(m1);
        }
    }
}