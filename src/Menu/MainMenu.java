package src.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("KELOMPOK APICK\n");
        System.out.println("MENU");
        System.out.print("""
        1. Sistem Persamaaan Linier
        2. Determinan
        3. Matriks balikan
        4. Interpolasi Polinom
        5. Interpolasi Bicubic Spline
        6. Regresi linier berganda
        7. Keluar
        Masukan pilihan: """);

        boolean keluar = false;
        int pilihan = 0;
        pilihan = scan.nextInt();
        
        while(pilihan<1 || pilihan>7){   //mengulangi input jika salah
            System.out.print("Masukan angka dengan benar: ");
            pilihan = scan.nextInt();
        }
        
        System.out.println();

        switch (pilihan){
            case 1:
                SPLMenu.menu();
            case 2:
                DeterminanMenu.main(args);
            case 3:
                InversMenu.menu();
            case 4:
                InterpolasiMenu.main(args);
            case 5:
                BicubicMenu.bikubik();
            case 6:
                RLBMenu.menu();
            case 7:
                keluar = true;
                break;
        }

        if (!keluar){
            System.out.println("""
            1. Kembali ke Menu
            2. Keluar
            Masukan pilihan anda: """);
    
            int pilihan2 = 0;
            pilihan2 = scan.nextInt();
    
            while(pilihan2<1 || pilihan2>2){   //mengulangi input jika salah
                System.out.print("Masukan angka dengan benar: ");
                pilihan = scan.nextInt();
            }
    
            if(pilihan2 == 1){
                main(args);
            }
        }
    }
}
