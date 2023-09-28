package src.Method;

public class Gauss_jordan {
    public static int getFirstOne(Matrix n, int row){
        int i = 0, index = 0;
        boolean found = false;
        while (i < n.col && found == false){
            if (n.m[row][i] == 1){
                index = i;
                found = true;
            }
            i++;
        }
        return index;
    }

    public static Matrix eliminasiGaussJordan(Matrix n, boolean x){
        n = Gauss.eliminasiGauss(n, x);
        int max_c = n.col;
        if (x == true){
            max_c -= 1;
        }
        int i,row = 1, col =0;
        while(row < max_c){
            col = getFirstOne(n, row);
            for (i = 0; i < row; i++){
                Gauss.add(n, i, row, -n.m[i][col]);
            }
            row++;
        }
        return n;
    }

    public static void main(String[] args){
        Matrix m1 = new Matrix(0, 0);
        m1.inputRowCol();
        m1.readMatrix();
        eliminasiGaussJordan(m1, false);
        m1.displayMatrix();
    }
}
