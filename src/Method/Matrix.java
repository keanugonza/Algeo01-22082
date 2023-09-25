package src;

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

        System.out.println("Masukan jumlah baris: ");
        row = scan.nextInt();
        System.out.println("Masukan jumlah kolom: ");
        col = scan.nextInt();
        
        this.row = row;
        this.col = col;
        this.m = new double[row][col];
    }

    //membaca matrix
    //I.S. Baris dan Kolom sudah terdefinisi. 
    public void readMatrix(){
        System.out.println("Masukan Matrix: ");
        for(int i=0;i<this.row;i++){
            for(int j=0;j<this.col;j++){
                this.m[i][j] = scan.nextDouble();
            }
        }
    }

    //Menampilkan matriks dengan type double 4 angka dibelakang koma
    public void displayMatrix(){
        for(int i=0;i<this.row;i++){
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
    //CARA PAKAI
    // int n = scan.nextInt();
    // Matrix m3 = new Matrix(n,n);
    // m3 = m3.createIdentitas();
    
    public void multiplyCons(double k){
        for (int i=0;i<this.row;i++){
            for (int j=0;j<this.col;j++){
                this.m[i][j] *= k;
            }
        }
    } 

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
    //CARA PAKAI
    // Matrix m4 = new Matrix(m1.row,m2.col);
    // m4 = m4.multiplyMatrix(m1, m2);
    // m4.displayMatrix();

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

    public Matrix transpose(){
        Matrix tp = new Matrix(this.row, this.col);
        for(int i=0;i<this.row;i++){
            for (int j=0;j<this.col;j++){
                tp.m[i][j] = this.m[i][j];
            }
         }
         return tp;
    }


    public boolean isSquare(){
        return(this.row==this.col);
    }

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
    
    //Cek apakah identitas atau bukan
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

    public boolean isMatrixNotEqual(Matrix m1, Matrix m2){
        return (!isMatrixEqual(m1, m2));
    }

    public boolean isMatrixSizeEqual(Matrix m1, Matrix m2){
        return ((m1.row == m2.row) && (m1.col == m2.col));
     }
    
    public int countElmt(){
        return (this.row * this.col);
    }
 
    public Matrix getMinor(Matrix m1,int row, int col){
        Matrix minor = new Matrix(m1.row-1,m1.col-1);
        for(int i=0; i<minor.row;i++){
            for(int j=0; j<minor.col;j++){
                if (i>=row && j<col){
                    minor.m[i][j] = m1.m[i+1][j];
                }
                else if (i>=row && j>=col){
                    minor.m[i][j] = m1.m[i+1][j+1];
                }
                else if (i<row && j>=col){
                    minor.m[i][j] = m1.m[i][j+1];
                }
                else if (i<row && j<col){
                    minor.m[i][j] = m1.m[i][j];
                }
            }
        }
    return minor;
    }

    public double determinan(Matrix m1){
        if (m1.row == 1){
            return m1.m[0][0];
        }
        else if (m1.row==2) {
            return ((m1.m[0][0]*m1.m[1][1])-(m1.m[0][1]*m1.m[1][0]));
        }

        double hasil =0;
        int sign =1 ;
      
        for (int i=0; i<m1.col;i++){
            hasil += sign * m1.m[0][i] * m1.determinan(m1.getMinor(m1,0,i));
            sign = -sign;
        }
        return hasil;
    }
}




