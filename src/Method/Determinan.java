package src.Method;

public class Determinan {
    public static Matrix getMinor(Matrix m1,int row, int col){
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

    public static double determinan(Matrix m1){
        if (m1.row == 1){
            return m1.m[0][0];
        }
        else if (m1.row==2) {
            return ((m1.m[0][0]*m1.m[1][1])-(m1.m[0][1]*m1.m[1][0]));
        }

        double hasil =0;
        int sign =1 ;
      
        for (int i=0; i<m1.col;i++){
            hasil += sign * m1.m[0][i] * determinan(getMinor(m1,0,i));
            sign = -sign;
        }
        return hasil;
    }
}
