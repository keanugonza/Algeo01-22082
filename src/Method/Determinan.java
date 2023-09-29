package src.Method;


public class Determinan { 

    //menghasilkan matrix minor tana baris row dan tanpa kolom col
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

    //menghasilkan determinan dari matrix dengan cara kofaktor.
    public static double determinanKofaktor(Matrix m1){
        if (m1.row == 1){
            return m1.m[0][0];
        }
        else if (m1.row==2) {
            return ((m1.m[0][0]*m1.m[1][1])-(m1.m[0][1]*m1.m[1][0]));
        }

        double hasil =0;
        int sign =1 ;
      
        for (int i=0; i<m1.col;i++){
            hasil += sign * m1.m[0][i] * determinanKofaktor(getMinor(m1,0,i));
            sign = -sign;
        }
        return hasil;
    }

    //menghasilkan determinan dari matrix dengan cara eliminasi baris
    public static double determinanGauss(Matrix m1){
        double det = 1;
        int r_now = 0, c_now = 0, i;


        while(r_now < m1.row && c_now < m1.col){
            int a;
            boolean end = false;
            while (end == false){
                for(a=r_now;a< m1.row;a++){
                    if (m1.m[a][c_now] != 0){
                        Gauss.swap(m1, r_now, a);
                        end = true;
                        if (r_now != a){
                            det *= -1;
                        }
                    }
                }
                end = true;
            }
            
            if (m1.m[r_now][c_now] != 0 ){
                det *= (m1.m[r_now][c_now]);
                Gauss.multi(m1, r_now, 1/m1.m[r_now][c_now]);
            }
            for (i = r_now + 1; i < m1.row; i++){
                Gauss.add(m1, i, r_now, -m1.m[i][c_now]);
            }
            r_now++;
            c_now++;
            
        }
        if (Math.ceil(det)-det > 0.5){
            return Math.floor(det);
        }
        else{
            return Math.ceil(det);
        }
    }
}
