// package src.Menu;

// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
// import java.util.Scanner;
// import javax.imageio.ImageIO;

// import src.Method.Invers;
// import src.Method.Matrix;
// import src.Menu.BicubicMenu;

// public class bonussMenu {
//     private static Scanner scan = new Scanner(System.in);

//     public static Matrix pixeltoMatrix (File file, int color) throws IOException{
//         BufferedImage img;
//         img = ImageIO.read(file);
//         int width = img.getWidth();
//         int height = img.getHeight();
//         Matrix n = new Matrix(height, width);
//         for (int y = 0; y < height; y++) {
//             for (int x = 0; x < width; x++) {
//                 int p = img.getRGB(x, y);
//                 int b = p & 0xff;
//                 int g = (p & 0xff00) >> 8;
//                 int r = (p & 0xff0000) >> 16;
//                 double blue = b;
//                 double green = g;
//                 double red = r;
//                 if (color == 1){
//                     n.m[x][y] = red;
//                 } else if (color == 2){
//                     n.m[x][y] = green;
//                 } else {
//                     n.m[x][y] = blue;
//                 }
//             }
//         }
//         return n;
//     }

//     public static Matrix Itolist(Matrix I){
//         Matrix l = new Matrix(16, 1);
//         int k = 0;
//         for(int i=0;i<4;i++){
//             for(int j=0;j<4;j++){
//                 l.m[k][0] = I.m[i][j];
//                 k++;
//             }
//         }
//         return l;
//     }

//     public static double[] fungsi1_I(int x,int y){
//         double[] l = new double[16];
//         int a=-1,b=-1;
//         for(int i=0;i<16;i++){
//             l[i] = 0;
//             if (a == x+1 && b == y){
//                 l[i] = 1;
//             }
//             a++;
//             if (a>2){
//                 a=-1;
//                 b++;
//             }
//         }
//         return l;
//     }

//     public static double[] fungsiX_I(int x,int y){
//         double[] l = new double[16];
//         int a=-1,b=-1;
//         for(int i=0;i<16;i++){
//             if (a == x-1 && b == y){
//                 l[i] = -0.5;
//             }
//             else if (a == x+1 && b == y){
//                 l[i] = 0.5;
//             } else {
//                 l[i] = 0;
//             }
//             a++;
//             if (a>2){
//                 a=-1;
//                 b++;
//             }
//         }
//         return l;
//     }

//     public static double[] fungsiY_I(int x,int y){
//         double[] l = new double[16];
//         int a=-1,b=-1;
//         for(int i=0;i<16;i++){
//             l[i] = 0;
//             if (a == x && b == y-1){
//                 l[i] = -0.5;
//             } else if (a == x && b == y+1){
//                 l[i] = 0.5;
//             }
//             a++;
//             if (a>2){
//                 a=-1;
//                 b++;
//             }
//         }
//         return l;
//     }

//     public static double[] fungsiXY_I(int x,int y){
//         double[] l = new double[16];
//         int a=-1,b=-1;
//         for(int i=0;i<16;i++){
//             l[i] = 0;
//             if (a == x - 1 && b == y){
//                 l[i] = -0.25;
//             } else if (a == x && b == y-1){
//                 l[i] = -0.25;
//             } else if (a == x && b == y){
//                 l[i] = -0.25;
//             } else if (a == x+1 && b == y+1){
//                 l[i] = 0.25;
//             }
//             a++;
//             if (a>2){
//                 a=-1;
//                 b++;
//             }
//         }
//         return l;
//     }

//     public static Matrix bikubikBonus(){
//         int i, x=0, y=0;
//         Matrix f = new Matrix(16, 16);

//         for (i=0; i<16; i++){
//             if (i < 4){
//                 f.m[i] = fungsi1(x, y);
//             } else if (4 <= i && i < 8){
//                 f.m[i] = fungsiX(x, y);
//             } else if (8 <= i && i < 12){
//                 f.m[i] = fungsiY(x, y);
//             } else {
//                 f.m[i] = fungsiXY(x, y);
//             }
//             x++;
//             if (x>1){
//                 x = 0;
//                 y++;
//             }
//             if (y>1){
//                 y = 0;
//             }
//         }
//         return f;
//     }

//     public static double[][][] colortoMatrix_aVal(Matrix n){
//         int x = 0,y = 0;
//         Matrix Xa = new Matrix(0, 0);
//         Matrix DI = new Matrix(16, 16);
//         Matrix XDI = new Matrix(0, 0);
//         Matrix I = new Matrix(4, 4);
//         Matrix tambahan = new Matrix(16, 1);
//         double[][][] a_val = new double[n.row][n.col][16];
//         for (int k = 0;k<16;k++){
//             if (k < 4){
//                 DI.m[k] = bonus2.fungsi1_I(x,y);
//             } else if (4 <= k && k < 8){
//                 DI.m[k] = bonus2.fungsiX_I(x, y);
//             } else if (8 <= k && k < 12){
//                 DI.m[k] = bonus2.fungsiY_I(x, y);
//             } else {
//                 DI.m[k] = bonus2.fungsiXY_I(x, y);
//             }
//             x++;
//             if (x>1){
//                 x = 0;
//                 y++;
//             }
//             if (y>1){
//                 y = 0;
//             }
//         }
//         Xa = bonus2.bikubikBonus();
//         Xa = Invers.inversGaussJordan(Xa);
//         XDI = Matrix.multiplyMatrix(DI,Xa);
//         for(int i=1;i<n.row-3; i++){
//             for(int j=1;j<n.col-3; j++){
//                 I = bonus2.bagi(n, 4, 4, i, j);
//                 tambahan = Itolist(I);
//                 a_val[i][j] = bonus2.splGaussBicubic(Matrix.mergeMatrix(XDI,tambahan));
//             }
//         }
//         return a_val;
//     }

//     public static Matrix a_valtoMatrixF(double[][][] a_val, double size, Matrix n){
//         int i = 1,j = 1,counti=0,countj = 0, q=0, r=0;
//         double diff = 1/(2*size-1),x=0,y=0;
//         int row = (int)((n.row-2)*size);
//         int col = (int)((n.col-2)*size);
//         int s = (int)(2*size);
//         Matrix f = new Matrix(row, col);
//         while (i+counti*s < row-1){
//             while (j + countj*s < col-1){
//                 f.m[i+counti*s][j+countj*s] = bonus2.sumFungsi1(a_val[q][r], BicubicMenu.fungsi1(x,y));
//                 y += diff;
//                 j++;
//                 if (j+countj*s > col - 2){
//                     counti++;
//                     countj = 0;
//                     q++;
//                 }
//                 if (j>size){
//                     y = diff;
//                 }
//                 if (j > s){
//                     y = 0;
//                     j = 1;
//                     i++;
//                     x += diff;
//                 }
//                 if (i>size){
//                     x = diff;
//                 }
//                 if (i > s){
//                     i = 1;
//                     countj++;
//                     r += 1;
//                 }
//             }
//         }
//         return f;
//     }
    
//     public static void main(String[] args) throws IOException{
//         double[][][] m;
//         System.out.println("Masukkan perbesaran yang diinginkan: ");
//         double size = scan.nextDouble();
//         Matrix r = new Matrix(0, 0), g = new Matrix(0, 0), b = new Matrix(0, 0);
//         Matrix n = new Matrix(0, 0);
//         n = pixeltoMatrix(new File("C:\\Users\\abdul\\OneDrive\\Documents\\bikubik2.jpg"), 1);
//         m = colortoMatrix_aVal(n);
//         r = a_valtoMatrixF(m, size, n);
//         r.displayMatrix();
//         n = pixeltoMatrix(new File("C:\\Users\\abdul\\OneDrive\\Documents\\bikubik2.jpg"), 2);
//         m = colortoMatrix_aVal(n);
//         g = a_valtoMatrixF(m, size, n);
//         n = pixeltoMatrix(new File("C:\\Users\\abdul\\OneDrive\\Documents\\bikubik2.jpg"), 3);
//         m = colortoMatrix_aVal(n);
//         b = a_valtoMatrixF(m, size, n);
//         BufferedImage img = new BufferedImage(r.col, r.row, BufferedImage.TYPE_INT_RGB);
//         for (int y = 0; y < r.row; y++) {
//             for (int x = 0; x < r.col; x++) {
//                 int p = img.getRGB(x, y);
  
//                 int red = (int)r.m[y][x];
//                 int green = (int)g.m[y][x];
//                 int blue = (int)b.m[y][x];
  
//                 // set new RGB keeping the r
//                 // value same as in original image
//                 // and setting g and b as 0.
//                 p = (red << 16 | green << 8 | blue);
  
//                 img.setRGB(x, y, p);
//             }
//         }
//         try {
//             File file = new File(
//                 "C:\\\\Users\\\\abdul\\\\OneDrive\\\\Documents\\\\resizedFoto.jpg");
//             ImageIO.write(img, "png", file);
//         }
//         catch (IOException e) {
//             System.out.println(e);
//         }
//     }
// }
