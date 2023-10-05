package src.Method;

public class Gauss {
    public static double rounding(double n){//membulatkan nilai yang selisih dengan bilangan bulat terdekat < 10^-8
        double x = Math.pow(10, -8), y = Math.abs(n)-Math.abs(n%1);
        if (y==0 && (Math.abs(n % 1) < x || Math.abs(1-(n%1)) < x)){
            n = 0;
        } else if (Math.abs(n % 1) < x){
            if (n<0){
                n += (n%1);
            } else{
                n -= (n%1);
            }
        } else if (Math.abs(1-(n%1)) < x){
            if (n<0){
                n -= (1-(n%1));
            } else{
                n += (1-(n%1));
            }
        }
        return n;
    }

    public static Matrix add(Matrix n, int f, int s, double x){
    //menambah setiap anggota dari baris f dengan setiap anggota dari baris s yang dikali dengan x
        int i, col = n.col;
        for (i=0;i<col;i++){
            n.m[f][i] = rounding(n.m[f][i] += n.m[s][i]*x);
        }
        return n;
    }

    public static Matrix swap(Matrix n, int r_1, int r_2){//menukar dua baris pada matriks n
        double[] f = n.m[r_1], s = n.m[r_2];
        n.m[r_2] = f;
        n.m[r_1] = s;
        return n;
    }

    public static Matrix multi(Matrix n, int row, double x){//mengalikan semua anggota baris row pada matriks n dengan x
        int i;
        for(i = 0; i < n.col;i++){
            n.m[row][i] = rounding(n.m[row][i] *= x);
        }
        return n;
    }

    public static Matrix divide(Matrix n, int row, double x){//membagikan semua anggota baris row pada matriks n dengan x
        int i;
        for(i = 0; i < n.col;i++){
            n.m[row][i] = rounding(n.m[row][i] /= x);
        }
        return n;
    }

    public static void first(Matrix n, int r_start, int col){
    //mengubah nilai matrik pada baris r_start dan kolom col jika nilainya nol 
    //dengan cara menukarnya dengan baris yang elemen kolom ke col nya tidak nol
        int i;
        boolean end = false;
        while (end == false){
            for(i=r_start;i< n.row;i++){
                if (n.m[i][col] != 0){
                    swap(n, r_start, i);
                    end = true;
                }
            }
            end = true;
        }
    }

    public static boolean colZero(Matrix n, int row, int col){
    //mengecek apakah anggota matriks pada kolom col dan berada pada baris row ke bawah bernilai nol semua 
        boolean found = true;
        for(int i=row; i< n.row;i++){
            if (rounding(n.m[i][col]) != 0){
                found = false;
            }
        }
        return found;
    }

    public static Matrix eliminasiGauss(Matrix n, boolean x){ //mengembalikan matriks n menjadi matriks eselon baris
        int row = 0, col = 0, i, max_r = n.row, max_c=n.col;
        double current_val;
        if (x == true){//jika x = true maka matriks n adalah matriks augmented
            max_c -= 1;
        }
        while(row < max_r && col < max_c){
            first(n, row, col);//mencegah current element (elemen pivot) bernilai nol
            boolean z = colZero(n, row, col);
            while (z && col < max_c){
            //selama anggota pada kolom saat ini dan pada baris row ke bawah bernilai nol semua,
            //current kolom berpindah ke kolom selanjutnya
                col++;
                z = colZero(n, row, col);
            }
            current_val = n.m[row][col];
            if (rounding(current_val) != 0){//jika nilai elemen saat ini bukan nol
                divide(n, row, current_val);//membagi elemen saat ini dibagi dengan dirinya sendiri agar mendapat nilai satu
            }
            for (i = row + 1; i < n.row; i++){
                add(n, i, row, -n.m[i][col]);//mengubah nilai di bawah satu utama menjadi nol
            }
            row++;
            col++;
        }
        return n;
    }

    public static void main(String[] args){
        Matrix m1 = new Matrix(0, 0);
        m1.inputRowCol();
        m1.readMatrix();
        eliminasiGauss(m1, true);
        m1.displayMatrix();
    }
}