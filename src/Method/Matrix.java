package src.Method;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Matrix{
    public int row;         //baris matrix
    public int col;         //kolom mattrix
    public double[][] m;    // matrix m
    private static Scanner scan = new Scanner(System.in);

    //menginiasi matrix
    //membuat matrix dengan baris dan kolom
    public Matrix(int row, int col){
        this.row = row;
        this.col = col;
        //mengisi semua elemen matriks dengan 0
        this.m = new double[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                this.m[i][j] = 0;
            }
        }
    }

    //melakukan proses input baris dan kolom matrix
    //F.S. baris dan kolom terdefinisi.
    public void inputRowCol(){
        int row = 0;
        int col = 0;

        System.out.print("Masukan jumlah baris: ");
        row = scan.nextInt();
        System.out.print("Masukan jumlah kolom: ");
        col = scan.nextInt();
        
        this.row = row;
        this.col = col;
        this.m = new double[row][col];
    }

    //membaca matrix
    //I.S. Baris dan Kolom sudah terdefinisi. 
    public void readMatrix(){
        System.out.println("Masukan Matrix: ");
        for(int i=0; i<this.row; i++){
            for(int j=0;j<this.col;j++){
                this.m[i][j] = scan.nextDouble();
            }
        }
    }

    //Menampilkan matriks dengan type double 4 angka dibelakang koma
    public void displayMatrix(){
        for(int i=0;i<this.row; i++){
            for(int j=0;j<this.col;j++){
                System.out.printf("%.4f ", this.m[i][j]);
            }
            System.out.println();

        }
    }

    
    
    //Membuat matrix identitas
    //I.S. Baris dan Kolom harus sama dan sudah terdefinisi
    public Matrix createIdentitas(){
        Matrix identitas = new Matrix(this.row,this.col);
        for (int i=0;i<this.row;i++){
            for (int j=0;j<this.col;j++){
                if(i==j){
                    identitas.m[i][j] = 1;
                }
            }
        }
        return identitas;
    }
    

    //mengalikan matrix dengan konstanta k
    public void multiplyCons(double k){
        for (int i=0;i<this.row;i++){
            for (int j=0;j<this.col;j++){
                this.m[i][j] *= k;
            }
        }
    } 


    //mengalikan matrix m1 dengan matrix m2.
    public Matrix multiplyMatrix(Matrix m1, Matrix m2){
        Matrix hasil = new Matrix(m1.row,m2.col);
        for (int i=0;i<hasil.row;i++){
            for (int j=0;j<hasil.col;j++){
                for (int k=0;k<m1.col;k++){
                    hasil.m[i][j] += m1.m[i][k] * m2.m[k][j];
                }
            }
        }
        return hasil;
    }


    //mengopy matrix ke matrix mIn.
    public void copyMatrix(Matrix mIn){
        for (int i=0;i<mIn.row;i++){
            for (int j=0;j<mIn.col;j++){
                this.m[i][j] = mIn.m[i][j];
            }
        }
    }


    //Menjumlahkan m1 dengan m2 (m1+m2)
    //I.S. ukuran m1 dan m2 harus sama
    public Matrix addMatrix(Matrix m1, Matrix m2){
        Matrix hasil = new Matrix(m1.row,m1.col);
        for (int i=0;i<m1.row;i++){
            for (int j=0;j<m1.col;j++){
                hasil.m[i][j] = m1.m[i][j] + m2.m[i][j];
            }
        }
        return hasil;
    }


    //Mengurangi m1 dengan m2 (m1-m2)
    //I.S. ukuran m1 dan m2 harus sama
    public Matrix substractMatrix(Matrix m1, Matrix m2){
        Matrix hasil = new Matrix(m1.row,m1.col);
        for (int i=0;i<m1.row;i++){
            for (int j=0;j<m1.col;j++){
                hasil.m[i][j] = m1.m[i][j] - m2.m[i][j];
            }
        }
        return hasil;
    }


    //menghasilkan transpose dari suatu matrix.
    public Matrix transpose(){
        Matrix tp = new Matrix(this.row, this.col);
        for(int i=0;i<this.row;i++){
            for (int j=0;j<this.col;j++){
                tp.m[i][j] = this.m[j][i];
            }
         }
         return tp;
    }


    //menghasilkan true apabila matrix merupakan square
    public boolean isSquare(){
        return(this.row==this.col);
    }

    //menghasilkan true apabila matrix symetric
    public boolean isSymmetric(){
        if (!isSquare()){
            return false;
         }
         else{
            for(int i=0;i<this.row;i++){
               for(int j=0;j<this.col;j++){
                  if (this.m[i][j] != this.m[j][i]){
                     return false;
                  }
               }
            }
            return true;
         }
    }
    

    //menghasilkan true apabila matrix identitas.
    public boolean isIdentity(){
        if (!isSquare()){
            return false;
        }
        for (int i=0;i<this.row;i++){
            for (int j=0;j<this.col;j++){
                if(i==j){
                    if(this.m[i][j]==0){
                        return false;
                    }
                }
                else{
                    if(this.m[i][j]!=0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    

    //menghasilkan true jika semuau elemen matrix m1 sama dengan matrix m2
    public boolean isMatrixEqual(Matrix m1, Matrix m2){
        if ( (m1.row != m2.row) || (m1.col != m2.col) ){
            return false;
         }
         else{
            for(int i=0;i<m1.row;i++){
               for(int j=0;j<m1.col;j++){
                  if (m1.m[i][j] != m2.m[i][j]){
                     return false;
                  }
               }
            }
            return true;
         }
    }

    //menghasilkan true jika elemen matrix m1 tidak sama dengan matrix m2.
    public boolean isMatrixNotEqual(Matrix m1, Matrix m2){
        return (!isMatrixEqual(m1, m2));
    }

    //menghasilkan true jika size matrix m1 sama dengan size matrix m2.
    public boolean isMatrixSizeEqual(Matrix m1, Matrix m2){
        return ((m1.row == m2.row) && (m1.col == m2.col));
     }
    
    //menghasilkan jumlah elemen pada sebuah matrix
    public int countElmt(){
        return (this.row * this.col);
    }

    //membaca sebuah file lalu memasukannya ke dalam matrix.
    public static Matrix fileToMatrix(String fileName) throws FileNotFoundException{
        File file  = new File(fileName);
        Scanner inputFile = new Scanner(file);
        
        int countRow , countCol;
        int i = 0;
        countRow = 0;
        countCol = 0;

        //mencari banyak baris dan kolom terlebih dahulu
        while(inputFile.hasNextLine()){
            String[] value = inputFile.nextLine().split(" ", -1);
            countCol = Math.max(countCol, value.length);
            countRow++;
        }
        inputFile.close();
        

        
        Matrix m1 = new Matrix(countRow,countCol); //membuat matrix kosong
        
        //mengisi ke matrix m1
        inputFile = new Scanner(file);
        while(inputFile.hasNextLine()){
            String[] value = inputFile.nextLine().split(" ",-1);
            if (value.length == countCol){ 
                for(int j=0;j<countCol;j++){
                    m1.m[i][j] = Double.parseDouble(value[j]);
                }
            }
            else{ //untuk mengatasi kasus pada file pada interpolasi dan bicubic
                for(int j=0;j<value.length;j++){
                m1.m[i][j] = Double.parseDouble(value[j]);
                }
            }
            i++;
        }
        inputFile.close();
        return m1;
    }


    //menyimpan matix kedalam file
    public static void saveMatrix(Matrix m1){
        System.out.println("Save hasil ke file (Y/N)?");
        String save = (scan.next()).toUpperCase();
        
        switch (save) {
            case "N":
                System.out.println("Matrix tidak tersimpan");
                break;

            case "Y":
            System.out.println("Masukan nama path file:");
                String file_name = scan.next();
                File File = new File(file_name);
                if(!(File.exists())){
                    try {
                        File.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try (FileWriter filewriter = new FileWriter(file_name)){
                    for(int i=0;i<m1.row;i++){
                        for(int j=0;j<m1.row;j++){
                            filewriter.write(String.format("%.4f ", m1.m[i][j]));
                        }
                    filewriter.write("\n");
                    }
                    System.out.println("Matrix sudah tersimpan di dalam file. ");
                }
                catch (IOException e){
                    System.out.println("Matrix GAGAL tersimpan.");
                }
                break;
        }
    }

    //Menyimpan String kedalam file.
    public static void saveString(String s){
        System.out.println("Save hasil ke file (Y/N)?");
        String save = (scan.next()).toUpperCase();
        
        switch (save) {
            case "N":
                System.out.println("Hasil tidak tersimpan");
                break;

            case "Y":
            System.out.print("Masukan nama path file: ");
                String file_name = scan.next();
                File File = new File(file_name);
                if(!(File.exists())){
                    try {
                        File.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try (FileWriter filewriter = new FileWriter(file_name)){
                    filewriter.write(s);
                    filewriter.write("\n");
                    System.out.println("Hasil sudah tersimpan di dalam file. ");
                }
                catch (IOException e){
                    System.out.println("Hasil GAGAL tersimpan.");
                }
                break;
        }
    }
    //CONTOH PEMAAKAIAN BISA LIAT DI determinanMenu.java


    public Matrix kofaktor(Matrix m1){
        Matrix result = new Matrix(m1.col, m1.row);
        double determinan;
        Matrix matrixTemp;
        for (int i = 0; i < m1.row; i++){
            for (int j = 0; j < m1.col; j++){
                matrixTemp = Determinan.getMinor(m1, i, j);
                determinan = Determinan.determinanKofaktor(matrixTemp);
                if ((i + j) % 2 == 0){
                    result.m[i][j] = determinan;
                } else {
                    result.m[i][j] = determinan * -1;
                }
            }
        }
        return result;
    }
    public Matrix adjoint(Matrix m1){
        Matrix result = new Matrix(m1.col, m1.row);
        Matrix matrixKofaktor = new Matrix(m1.col, m1.row);
        matrixKofaktor = kofaktor(m1);
        result = matrixKofaktor.transpose();
        
        return result;
    }

    public static Matrix mergeMatrix(Matrix m1, Matrix m2){
        Matrix result = new Matrix(m1.row, m1.col + m2.col);
        for (int i = 0; i < result.row; i++){
            for (int j = 0; j < m1.col; j++){
                result.m[i][j] = m1.m[i][j];
            }
            for (int k = m1.col; k < result.col; k++){
                result.m[i][k] = m2.m[i][k - m1.col];
            }
        }

        return result;
    }

    public void addRow(Matrix m1, int row1, int row2, double l){
        for (int i = 0; i < m1.col; i++){
            m1.m[row1][i] += l * m1.m[row2][i];
        }
    }

    public Matrix buatIdentitas(int k){
        Matrix ident = new Matrix(k, k);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (i == j) {
                    ident.m[i][j] = 1;
                } else {
                    ident.m[i][j] = 0;
                }
            }
        }
        return ident;
    }

    public void splitMatrix(Matrix m1, Matrix m2, int col){
        m1.row = this.row;
        m2.row = this.row;
        m1.col = col;
        m2.col = this.col - m1.col;
        m1.m = new double[m1.row][m1.col];
        m2.m = new double[m2.row][m2.col];
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < col; j++){
                m1.m[i][j] = this.m[i][j];
            }
        }
        for (int i = 0; i < this.row; i++){
            int k = 0;
            for (int j = col; j < this.col; j++){
                m2.m[i][k] = this.m[i][j];
                k++;
            }
        }
    }

    public void inputSquareMatrix() {
        int n = 0;
        System.out.print("Input harus berupa matriks segiempat dengan ukuran n x n. Masukkan n: ");
        try {
            n = scan.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        this.row = n;
        this.col = n;
        this.m = new double[this.row][this.col];
        this.readMatrix();
    }

    public static Matrix eadFile(String fileName) {
        return null;
    }
 
}




