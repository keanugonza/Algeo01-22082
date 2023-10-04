package src.Menu;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.Method.Gauss_jordan;
import src.Method.Invers;
import src.Method.Matrix;
import src.Method.Cramer;
import src.Method.Gauss;

public class SPLMenu {
    private static Scanner scan = new Scanner(System.in);
    public static boolean checkMatrix(Matrix n){ 
        //mengecek apakah ada baris yang bernilai nol semua kecuali nilai b (kolom terakhir)
        int i = n.row-1, j=0, count = 0;
        boolean found = false;
        while (found == false && i >= 0){
            if (Gauss.rounding(n.m[i][j]) == 0){
                count++;
            }
            j++;
            if (j > n.col - 1){
                j = 0;
                i -= 1;
                if(count == n.col-1){
                    found = true;
                }
                count = 0;
            }
        }
        return found;
    }

    public static int zeroRow(Matrix n){ //mengecek apakah ada baris yang semua kolomnya memiliki nilai nol
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

    public static int getOther(double[] l){ //mengembalikan banyaknya nilai bukan nol di antara kolom letak satu pertama dan kolom terakhir
        int i, count = 0;
        for (i = 0; i < l.length - 1; i++){
            if (l[i] != 0){
                count += 1;
            }
        }
        return count - 1;
    }

    public static void splGauss(Matrix m1){
        int i,j, r_zero = 0, col_f = 1;
        String persamaan = "";
        boolean t_ada;
        Gauss.eliminasiGauss(m1, true);
        if (checkMatrix(m1)){
            System.out.println("Persamaan tidak memiliki penyelesaian.");
            persamaan += "Persamaan tidak memiliki penyelesaian.";
            Matrix.saveString(persamaan);
        } else {
            r_zero = zeroRow(m1);
            //iterasi kecuali di row yang semua anggotanya bernilai nol
            for (i = 0; i < m1.row - r_zero; i++){
                for (j = i+1; j < m1.row - r_zero; j++){
                    Gauss.add(m1, i, j, -m1.m[i][Gauss_jordan.getFirstOne(m1,j)]);
                }
            }
            for (i = 0; i < m1.row - r_zero; i++){
                t_ada = false;
                if (r_zero == 0 && m1.row == m1.col - 1){
                    //jika solusi unik
                    persamaan += String.format("x%d = %.4f\n", i + 1, m1.m[i][m1.col-1]);
                } else {
                    int x;
                    x = Gauss_jordan.getFirstOne(m1, i); //menyimpan index kolom 1 pertama di baris i pada x
                    //mengecek nilai b (kolom terakhir)
                    if (m1.m[i][m1.col-1] != 0){ //jika nilai b bukan nol, tulis nilainya
                        persamaan += String.format("x%d = %.4f", x + 1, m1.m[i][m1.col-1]);
                    } else {
                        if (getOther(m1.m[i]) > 0){
                        //jika ada nilai bukan nol di antara satu pertama dan b, maka akan ada variabel lain(parametrik)
                            persamaan += String.format("x%d = ", x + 1);
                            t_ada = true; //menunjukkan tidak perlu perlu + - untuk variable parametrik
                        } else {
                        //tidak ada variabel lain, maka b tetap ditulis 0
                            persamaan += String.format("x%d = %.4f", x + 1, m1.m[i][m1.col-1]);
                        }
                    }
                    if (getOther(m1.m[i]) > 0){
                        col_f = Gauss_jordan.getFirstOne(m1,i) + 1; //menyimpan index kolom satu pertama
                        while (col_f < m1.col - 1){ //iterasi sampai sebelum nilai b
                            double current_val = m1.m[i][col_f];
                            if (current_val != 0){
                                if (t_ada == false){ //nilai b bukan nol jadi perlu tanda + -
                                    if (current_val < 0){
                                        persamaan += " + ";
                                    } else {
                                        persamaan += " - ";
                                    }
                                }
                            if (Math.abs(current_val) == 1){//jika koefesien x 1 atau -1, cukup ditulis x saja
                                persamaan += String.format("x%d", col_f + 1);
                            } else{
                                if (current_val < 0){
                                //jika nilai koefesien di kiri bernilai negatif, maka ketika dipindah ke kanan berubah menjadi positif
                                    persamaan += String.format("%.4fx%d", -m1.m[i][col_f],col_f + 1);
                                } else {
                                    persamaan += String.format("%.4fx%d", m1.m[i][col_f],col_f + 1);
                                }
                            }
                            }
                            col_f += 1;
                        }
                    }
                    persamaan += "\n";
                }
            }
            System.out.println("Solusi dari persamaan adalah: ");
            System.out.println(persamaan);
            Matrix.saveString(persamaan);
        }
    }

    public static void splGaussJordan(Matrix m1){
        int i, r_zero = 0, col_f = 1;
        boolean t_ada;
        String persamaan = "";
        Gauss_jordan.eliminasiGaussJordan(m1, true);
        if (checkMatrix(m1) == true){
            System.out.println("Persamaan tidak memiliki penyelesaian.");
            persamaan += "Persamaan tidak memiliki penyelesaian.";
            Matrix.saveString(persamaan);
        } else {
            r_zero = zeroRow(m1);
            for (i = 0; i < m1.row - r_zero; i++){
                t_ada = false;
                if (r_zero == 0 && m1.row == m1.col - 1){
                    //jika solusi unik
                    persamaan += String.format("x%d = %.4f\n", i + 1, m1.m[i][m1.col-1]);
                } else {
                    int x;
                    x = Gauss_jordan.getFirstOne(m1, i); //menyimpan index kolom 1 pertama di baris i pada x
                    //mengecek nilai b (kolom terakhir)
                    if (m1.m[i][m1.col-1] != 0){ //jika nilai b bukan nol, tulis nilainya
                        persamaan += String.format("x%d = %.4f", x + 1, m1.m[i][m1.col-1]);
                    } else {
                        if (getOther(m1.m[i]) > 0){
                        //jika ada nilai bukan nol di antara satu pertama dan b, maka akan ada variabel lain(parametrik)
                            persamaan += String.format("x%d = ", x + 1);
                            t_ada = true; //menunjukkan tidak perlu perlu + - untuk variable parametrik
                        } else {
                        //tidak ada variabel lain, maka b tetap ditulis 0
                            persamaan += String.format("x%d = %.4f", x + 1, m1.m[i][m1.col-1]);
                        }
                    }
                    if (getOther(m1.m[i]) > 0){
                        col_f = Gauss_jordan.getFirstOne(m1,i) + 1; //menyimpan index kolom satu pertama
                        while (col_f < m1.col - 1){ //iterasi sampai sebelum nilai b
                            double current_val = m1.m[i][col_f];
                            if (current_val != 0){
                                if (t_ada == false){ //nilai b bukan nol jadi perlu tanda + -
                                    if (current_val < 0){
                                        persamaan += " + ";
                                    } else {
                                        persamaan += " - ";
                                    }
                                }
                            if (Math.abs(current_val) == 1){//jika koefesien x 1 atau -1, cukup ditulis x saja
                                persamaan += String.format("x%d", col_f + 1);
                            } else{
                                if (current_val < 0){
                                //jika nilai koefesien di kiri bernilai negatif, maka ketika dipindah ke kanan berubah menjadi positif
                                    persamaan += String.format("%.4fx%d", -m1.m[i][col_f],col_f + 1);
                                } else {
                                    persamaan += String.format("%.4fx%d", m1.m[i][col_f],col_f + 1);
                                }
                            }
                            }
                            col_f += 1;
                        }
                    }
                    persamaan += "\n";
                }
            }
            System.out.println("Solusi dari persamaan adalah: ");
            System.out.println(persamaan);
            Matrix.saveString(persamaan);
        }
    }

    public static void splInversBalikan(Matrix m1){ //SPL metode invers matriks
        int j;
        String result = new String();
        Matrix hasil = new Matrix(0, 0);
        Matrix matrixMain = new Matrix(0, 0);
        Matrix matrixEq = new Matrix(0, 0);
        m1.splitMatrix(matrixMain, matrixEq, m1.col - 1);
        matrixMain = Invers.inversAdjoint(matrixMain);
        if (matrixMain != null) { //jika return dari fungsi invers tidak null artinya SPL memiliki penyelesaian
            System.out.println();
            System.out.println("Matriks balikan: ");
            matrixMain.displayMatrix();
            hasil = hasil.multiplyMatrix(matrixMain, matrixEq);
            for (j = 0; j < hasil.row; j++){ //menyusun string untuk output
                result += ("x" + (j + 1) + " = " + String.format("%.4f", hasil.m[j][0]) + "\n");
            }
            System.out.println(result);
            Matrix.saveString(result);
        } else { //jika return dari fungsi invers null artinya SPL tidak memeiliki penyelesaian
            result = "Matriks tidak memiliki balikan sehingga tidak bisa diselesaikan. Coba metoed lain";
            System.out.println();
            System.out.println(result);
            Matrix.saveString(result);
        }
        
    }

    public static void splCramer(Matrix m1){ //invers metode cramer 
        String result = new String();
        result = Cramer.cramer(m1);
        System.out.println(result);
        Matrix.saveString(result);
    }

    public static void menu() throws FileNotFoundException { //menu untuk SPL
        System.out.println();
        System.out.println("Sistem Persamaan Linear");
        System.out.println("1. Keyboard input");
        System.out.println("2. File input");
        System.out.print("Masukkan pilihan input: ");
        int opt = 0;
        int method;
        boolean inputValid = true;
        Matrix inputMat = new Matrix(0, 0);
        try {
            opt = scan.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        switch(opt) {
            case 1: //masukan dari keyboard
                inputMat.inputRowCol();
                inputMat.readMatrix();
                break;
            case 2: //masukan dari file
                Scanner scan = new Scanner(System.in);
                try {
                System.out.println("Input nama path file anda : ");    
                String fileName = scan.nextLine();
                inputMat = Matrix.fileToMatrix(fileName);
                } catch (FileNotFoundException e) {
                    System.out.println("Tidak ditemukan file.");
                    inputValid = false;
                }
                break;
            default:
                inputValid = false; //tidak valid maka program selanjutnya tidak dieksekusi
                System.out.println("Input anda kurang tepat. Mohon masukkan 1 atau 2.\n");
                menu(); //kembali ke menu input sampai benar 1 atau 2
        }
        if (inputValid) {
            System.out.println("""
        Pilih metode penyelesaian:
        1. Metode eliminasi Gauss
        2. Metode eliminasi Gauss-Jordan
        3. Metode matriks balikan
        4. Kaidah Cramer
        """);
        method = scan.nextInt();
        while (method != 1 && method != 2 && method != 3 && method != 4){ // akan menglang sampai input benar 1,2,3 atau 4
            System.out.print("Mohon masukan angka 1, 2, 3, atau 4\n");
            method = scan.nextInt();
        }
        switch (method){
            case 1: // untuk metode gauss
                splGauss(inputMat);
                break;
            case 2: // untuk metode gauss jordan
                splGaussJordan(inputMat);
                break;
            case 3: // untuk metode invers
                splInversBalikan(inputMat);
                break;
            case 4: // untuk metode cramer 
                splCramer(inputMat);
                break;
        }
        }
    }
}