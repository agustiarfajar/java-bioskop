/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop;

import java.util.Scanner;

public class Menu_User {
    private int id_user;
    
    public static final String BLACK = "\u001b[30m";
    public static final String GREEN = "\u001b[32m";
    public static final String BLUE = "\u001b[34m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    
    Scanner key = new Scanner(System.in);

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public void program() {         
        Pemesanan pemesanan = new Pemesanan();
        Menu_Login login = new Menu_Login();
        int pilihan;
        System.out.println("");
        System.out.println(CYAN+"________________________________________");
        System.out.println(CYAN+"| Halo, Selamat Datang di Bioskop Apps |");
        System.out.println(CYAN+"+======================================+");
        System.out.println(CYAN+"|             "+BLACK+"MENU USER"+CYAN+"                |");
        System.out.println(CYAN+"+======================================+");
        System.out.println(CYAN+"| "+BLACK+"1. Pesan Tiket Bioskop"+CYAN+"               |");
        System.out.println(CYAN+"| "+BLACK+"2. Tiket Saya"+CYAN+"                        |");
        System.out.println(CYAN+"| "+BLACK+"3. Log out"+CYAN+"                           |");
        System.out.println(CYAN+"+======================================+");
        do {
            System.out.print("Pilihan Anda [1/2/3] : ");
            pilihan = key.nextInt();
            switch (pilihan) {
                case 1:
                    pemesanan.setId_user(getId_user());
                    pemesanan.pemesanan();
                    break;
                case 2:
                    pemesanan.setId_user(getId_user());
                    pemesanan.myTiket();
                    break;
                case 3:
                    System.out.println("Logout berhasil..!");
                    System.out.println("");
                    login.program();
                    break;
                default:
                    System.err.println("Pilihan tidak tersedia !");
                    break;
            }
        } while (pilihan != 3);
    }
    
    public void konfirmasi() {
        char konfirmasi;
        
        System.out.println("");
        do {
            System.out.print("Lanjutkan Program [Y/T] : ");
            konfirmasi = key.next().charAt(0);
            konfirmasi = Character.toUpperCase(konfirmasi);
            switch (konfirmasi) {
                case 'Y':
                    program();
                    break;
                case 'T':
                    System.exit(0);
                    break;
                default:
                    System.err.println("Pilihan Tidak Tersedia!");
                    break;
            }
        } while (konfirmasi != 'T');
    }
}
