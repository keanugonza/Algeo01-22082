package src.Method;

public class Invers {
        
    public static Matrix inversAdjoint(Matrix m1){
        Matrix result = new Matrix(m1.row, m1.col);
        Matrix adj = new Matrix(m1.row, m1.col);
        double determinan = Determinan.determinanKofaktor(m1);
        if (determinan == 0){
            
            return null;
        } else {
            adj = adj.adjoint(m1);
            adj.multiplyCons(1 / determinan);
            
            for (int i = 0; i < adj.row; i++){
                for (int j = 0; j < adj.col; j++){
                    if (adj.m[i][j] == -0){
                        adj.m[i][j] = 0;
                    }
                }
            }
            result = adj;

            return result;
        }
    }

    public static Matrix inversGaussJordan(Matrix m1){
        Matrix result = new Matrix(m1.row, m1.col);
        Matrix merge = new Matrix(m1.row, m1.col);
        result = result.createIdentitas();
        merge = Matrix.mergeMatrix(m1, result);
        merge = Gauss.eliminasiGauss(merge, false);
        int cekDiag = m1.row - 1;
        while (merge.m[cekDiag][cekDiag] != 0 && cekDiag > 0){
            for (int i = cekDiag - 1; i >= 0; i--){ 
                merge.addRow(merge, i, cekDiag, -(merge.m[i][cekDiag]));
            }
            cekDiag--;
        }
        if (merge.m[cekDiag][cekDiag] != 0){
            for (int i = 0; i < m1.row; i++){
                for (int j = 0; j < m1.col; j++){
                    result.m[i][j] = merge.m[i][m1.col + j];
                }
            }
        } else { //tidak terdapat inverse
            
            return null;
        }

        return result;

    }
}