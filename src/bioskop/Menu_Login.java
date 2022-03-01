/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Fajar
 */
public class Menu_Login {
    
    public void program() {
        Scanner key = new Scanner(System.in);
        int pilihan;
        
        Akun akun = new Akun();
        try {
            System.out.println("+==============================================+");
            System.out.println("|        Selamat Datang di Bioskop Apps        |");
            System.out.println("+==============================================+");
            System.out.println("|  1. Anda pengguna baru? Silahkan Registrasi  |");
            System.out.println("|  2. Masuk / Log In                           |");
            System.out.println("|  3. Keluar                                   |");
            System.out.println("+==============================================+");           
            do {
                System.out.print("Pilihan [1/2/3] : ");
                pilihan = key.nextInt();
                switch (pilihan) {
                    case 1:
                        akun.daftar_akun();
                        break;
                    case 2:
                        akun.masuk();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.err.println("Pilihan tidak tersedia");
                        break;
                }
            } while (pilihan != 3);  
        } catch (InputMismatchException e) {
            System.err.println("Inputan harus berupa angka!");
        }  
  }
}
