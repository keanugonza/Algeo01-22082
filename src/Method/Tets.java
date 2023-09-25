package src.Method;

public class Tets {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(0,0); 
        m1.inputRowCol();
        m1.readMatrix();
        if(m1.isIdentity()){
            System.out.println("Identitas");
        }
        else{
            System.out.println("Bukan Identitas");
        }

        m1 = Determinan.getMinor(m1, 0, 0);
        double det;
        det = Determinan.determinan(m1);
        System.out.println(det);
        m1.displayMatrix();
    
    } 
    // Matrix m2 = new Matrix(0,0);
    // m2.inputRowCol();
    // m2.readMatrix();
    // if(m2.isIdentity()){
    //     System.out.println("Identitas");
    // }
    // else{
    //     System.out.println("Bukan Identitas");
    // }
    
    // Matrix m4 = new Matrix(m1.row,m2.col);

    
    // System.out.println("Add m1 dan m2: ");
    // m4 = m4.addMatrix(m1,m2);
    // m4.displayMatrix();


    // System.out.println("Substract m1 dan m2: ");
    // m4 = m4.substractMatrix(m1,m2);
    // m4.displayMatrix();

    // m4 = m4.multiplyMatrix(m1, m2);
    // System.out.println("Multiply m1 dan m2: ");
    // m4.displayMatrix();
    
    // System.out.println("Copy m1 ke m4:");
    // m4.copyMatrix(m1);
    // m4.displayMatrix();


    // int n = scan.nextInt();
    // Matrix m3 = new Matrix(n,n);
    // m3 = m3.createIdentitas();
    // m3.displayMatrix();

}
