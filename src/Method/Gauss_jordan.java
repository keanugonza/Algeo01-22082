package src.Method;

public class Gauss_jordan {
    public static int getFirstOne(Matrix n, int row){
    //mengembalikan index satu pertama pada baris row
        int i = 0, index = 0;
        boolean found = false;
        while (i < n.col-1 && found == false){
            if (n.m[row][i] == 1){
                index = i;
                found = true;
            }
            i++;
        }
        return index;
    }

    public static Matrix eliminasiGaussJordan(Matrix m1, boolean x){
        m1 = Gauss.eliminasiGauss(m1, x);
        for (int i = 0; i < m1.row ; i++){
            for (int j = i+1; j < m1.row ; j++){
            //mengubah nilai di atas satu utama menjadi nol
                Gauss.add(m1, i, j, -m1.m[i][Gauss_jordan.getFirstOne(m1,j)]);
            }
        }
        return m1;
    }

    public static void main(String[] args){
        Matrix m1 = new Matrix(0, 0);
        m1.inputRowCol();
        m1.readMatrix();
        eliminasiGaussJordan(m1, false);
        m1.displayMatrix();
    }
}
