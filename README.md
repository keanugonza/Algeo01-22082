# Tugas Besar 1 Aljabar Linier dan Geometri IF 2123

Kelompok APICK :
Keanu Amadius Gonza Wrahatno - 13522082
Abdullah Mubarak - 13522101
Dimas Bagoes Hendrianto - 13522112

## Deskripsi

Tugas besar membuat semacam kalkulator matriks yang menggunakan bahasa Java yang dapat menghitung berbagai operasi dan modifikasi matriks. Menggunakan beberapa metode program ini bisa digunakan untuk menghitung determinan, mencari invers, menyelesaikan Sistem Persamaan Linear, mencari interpolasi matriks, menghitung Regresi Linear Berganda, serta mencari Bicubic. Tidak kami tetapkan batasan untuk ukuran matriks, jadi pengguna bebas dalam menggunakan program ini.

## Struktur File

TUBES-ALGEO-APICK <br>

<!-- ┣ bin/ <br>
┃ ┣ Aplikasi/ <br>
┃ ┃ ┣ BicubicInterpolationApp.class <br>
┃ ┃ ┣ DeterminantApp.class <br>
┃ ┃ ┣ InterpolasiApp.class <br>
┃ ┃ ┣ InverseApp.class <br>
┃ ┃ ┣ RLBApp.class <br>
┃ ┃ ┗ SPLApp.class <br>
┃ ┣ Matrix/ <br>
┃ ┃ ┣ Determinant.class <br>
┃ ┃ ┣ Inverse.class <br>
┃ ┃ ┣ Matrix.class <br>
┃ ┃ ┗ SPL.class <br>
┃ ┣ Utils/ <br>
┃ ┃ ┗ Utils.class <br>
┃ ┗ Main.class <br>
┣ lib/ <br> -->

┣ src/ <br>
┃ ┣ Menu/ <br>
┃ ┃ ┣ BicubicMenu.java <br>
┃ ┃ ┣ DeterminanMenu.java <br>
┃ ┃ ┣ InterpolasiMenu.java <br>
┃ ┃ ┣ InverseMenu.java <br>
┃ ┃ ┣ MainMenu.java <br>
┃ ┃ ┣ RLBMenu.java <br>
┃ ┃ ┗ SPLMenu.java <br>
┃ ┣ Method/ <br>
┃ ┃ ┣ Cramer.java <br>
┃ ┃ ┣ Determinan.java <br>
┃ ┃ ┣ Gauss_jordan.java <br>
┃ ┃ ┣ Gauss.java <br>
┃ ┃ ┣ Invers.java <br>
┃ ┃ ┗ Matrix.java <br>
┣ Test/ <br>
┃ ┣ Input/ <br>
┃ ┗ Output/ <br>

<!-- ┣ .gitignore <br> -->

┗ README.md <br>

<!-- ## How to run

Clone this repo https://github.com/keanugonza/Tubes-Algeo-Apick.git

### Using .class

1. Go to folder bin `cd bin`
2. Open in terminal
3. Run Main.class `java Main`
4. File inputs can be put into the `./test` folder

### Using .jar

1. Download the .jar file that is located in `./lib`
2. Put the .jar file in a certain folder
3. In root of the folder that contains .jar file, create a new folder named `test` for file input
4. cd to the folder containing the .jar file
5. Use `java -jar <jar-file-name>` to run the program -->
