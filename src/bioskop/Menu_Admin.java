/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop;

import java.util.Scanner;

/**
 *
 * @author Fajar
 */
public class Menu_Admin {
    private int id_user;
    
    public static final String BLACK = "\u001b[30m";
    public static final String GREEN = "\u001b[32m";
    public static final String BLUE = "\u001b[34m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    
    Scanner key = new Scanner(System.in);
    Menu_Login login = new Menu_Login();

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public void program() {
        Scanner input = new Scanner(System.in);
        Scanner masukan = new Scanner(System.in);
        Pemesanan pemesanan = new Pemesanan();
        
        int pilihan;
        System.out.println(MAGENTA+"+========================================+");
        System.out.println(MAGENTA+"|         "+BLACK+"Selamat Datang Admin"+MAGENTA+"           |");
        System.out.println(MAGENTA+"+========================================+");
        System.out.println(MAGENTA+"|  "+BLACK+"1. CRUD Film"+MAGENTA+"                          |");
        System.out.println(MAGENTA+"|  "+BLACK+"2. CRUD Daerah"+MAGENTA+"                        |");
        System.out.println(MAGENTA+"|  "+BLACK+"3. CRUD Studio"+MAGENTA+"                        |");
        System.out.println(MAGENTA+"|  "+BLACK+"4. CRUD Kursi"+MAGENTA+"                         |");
        System.out.println(MAGENTA+"|  "+BLACK+"5. DATA Pemesanan"+MAGENTA+"                     |");
        System.out.println(MAGENTA+"|  "+BLACK+"6. Log Out"+MAGENTA+"                            |");
        System.out.println(MAGENTA+"+========================================+");
        do {
            System.out.print("Pilihan Anda [1/2/3/4/5/6] : ");
            pilihan = masukan.nextInt();
            
            switch (pilihan){
                case 1:
                    int pilFilm;
                    Crud_Film crudFilm = new Crud_Film();
                    System.out.println("");
                    System.out.println(MAGENTA+"+========================================+");
                    System.out.println(MAGENTA+"|               "+BLACK+"CRUD Film"+MAGENTA+"                |");
                    System.out.println(MAGENTA+"+========================================+");
                    System.out.println(MAGENTA+"|  "+BLACK+"1. Menambahkan Data Film"+MAGENTA+"              |");
                    System.out.println(MAGENTA+"|  "+BLACK+"2. Melihat Data Film"+MAGENTA+"                  |");
                    System.out.println(MAGENTA+"|  "+BLACK+"3. Mengubah Data Film"+MAGENTA+"                 |");
                    System.out.println(MAGENTA+"|  "+BLACK+"4. Menghapus Data Film"+MAGENTA+"                |");
                    System.out.println(MAGENTA+"|  "+BLACK+"5. Kembali            "+MAGENTA+"                |");
                    System.out.println(MAGENTA+"+========================================+");
                    do {
                        System.out.print("Pilihan Anda [1/2/3/4] : ");
                        pilFilm = input.nextInt();

                        switch (pilFilm){
                            case 1:
                                crudFilm.menambahkanFilm();
                                System.out.println("");
                            break;

                            case 2:
                                crudFilm.menampilkanFilm();
                                System.out.println("");
                            break;

                            case 3:
                                crudFilm.mengeditFilm();
                                System.out.println("");
                            break;

                            case 4:
                                crudFilm.menghapusFilm();
                                System.out.println("");
                                break;
                            case 5:
                                program();
                                break;
                            default:
                                System.err.println("Pilihan tidak tersedia..!");
                                break;
                        } 
                    } while (pilFilm != 5); 
                break;

                case 2:
                    int pilDaerah;  
                    Crud_Daerah crudDaerah = new Crud_Daerah();
                    System.out.println("");
                    System.out.println(MAGENTA+"+========================================+");
                    System.out.println(MAGENTA+"|               "+BLACK+"CRUD Daerah"+MAGENTA+"              |");
                    System.out.println(MAGENTA+"+========================================+");
                    System.out.println(MAGENTA+"|  "+BLACK+"1. Menambahkan Data Daerah"+MAGENTA+"            |");
                    System.out.println(MAGENTA+"|  "+BLACK+"2. Melihat Data Daerah"+MAGENTA+"                |");
                    System.out.println(MAGENTA+"|  "+BLACK+"3. Mengubah Data Daerah"+MAGENTA+"               |");
                    System.out.println(MAGENTA+"|  "+BLACK+"4. Menghapus Data Daerah"+MAGENTA+"              |");
                    System.out.println(MAGENTA+"|  "+BLACK+"5. Kembali            "+MAGENTA+"                |");
                    System.out.println(MAGENTA+"+========================================+");
                    do {
                        System.out.print("Pilihan Anda [1/2/3/4] : ");
                        pilDaerah = input.nextInt();

                        switch(pilDaerah){
                            case 1:
                                crudDaerah.tambahDaerah();
                                System.out.println("");
                            break;

                            case 2:
                                crudDaerah.tampilDaerah();
                                System.out.println("");
                            break;

                            case 3:
                                crudDaerah.ubahDaerah();
                                System.out.println("");
                            break;

                            case 4:
                                crudDaerah.hapusDaerah();
                                System.out.println("");
                                break;
                            case 5:
                                program();
                                break;
                            default:
                                System.err.println("Pilihan tidak tersedia..!");
                                break;
                        }
                    } while (pilDaerah != 5);
                break;

                case 3:
                    int pilStudio;
                    Crud_Studio crudStudio = new Crud_Studio();
                    System.out.println("");
                    System.out.println(MAGENTA+"+========================================+");
                    System.out.println(MAGENTA+"|               "+BLACK+"CRUD Studio"+MAGENTA+"              |");
                    System.out.println(MAGENTA+"+========================================+");
                    System.out.println(MAGENTA+"|  "+BLACK+"1. Menambahkan Data Studio"+MAGENTA+"            |");
                    System.out.println(MAGENTA+"|  "+BLACK+"2. Melihat Data Studio"+MAGENTA+"                |");
                    System.out.println(MAGENTA+"|  "+BLACK+"3. Mengubah Data Studio"+MAGENTA+"               |");
                    System.out.println(MAGENTA+"|  "+BLACK+"4. Menghapus Data Studio"+MAGENTA+"              |");
                    System.out.println(MAGENTA+"|  "+BLACK+"5. Kembali            "+MAGENTA+"                |");
                    System.out.println(MAGENTA+"+========================================+");
                    do {
                        System.out.print("Pilihan Anda [1/2/3/4] : ");
                        pilStudio = input.nextInt();

                        switch(pilStudio){
                            case 1:
                                crudStudio.tambahStudio();
                                System.out.println("");
                            break;

                            case 2:
                                crudStudio.tampilStudio();
                                System.out.println("");
                            break;

                            case 3:
                                crudStudio.ubahStudio();
                                System.out.println("");
                            break;

                            case 4:
                                crudStudio.hapusStudio();
                                System.out.println("");
                                break;
                            case 5:
                                program();
                                break;
                            default:
                                System.err.println("Pilihan tidak tersedia..!");
                                break;
                        }
                    } while (pilStudio != 5);
                break;

                case 4:
                    int pilKursi;
                    Crud_Kursi crudKursi = new Crud_Kursi();
                    System.out.println("");
                    System.out.println(MAGENTA+"+========================================+");
                    System.out.println(MAGENTA+"|               "+BLACK+"CRUD Kursi"+MAGENTA+"               |");
                    System.out.println(MAGENTA+"+========================================+");
                    System.out.println(MAGENTA+"|  "+BLACK+"1. Menambahkan Data Kursi"+MAGENTA+"             |");
                    System.out.println(MAGENTA+"|  "+BLACK+"2. Melihat Data Kursi"+MAGENTA+"                 |");
                    System.out.println(MAGENTA+"|  "+BLACK+"3. Mengubah Data Kursi"+MAGENTA+"                |");
                    System.out.println(MAGENTA+"|  "+BLACK+"4. Menghapus Data Kursi"+MAGENTA+"               |");
                    System.out.println(MAGENTA+"|  "+BLACK+"5. Kembali            "+MAGENTA+"                |");
                    System.out.println(MAGENTA+"+========================================+");
                    do {
                        System.out.print("Pilihan Anda [1/2/3/4] : ");
                        pilKursi = input.nextInt();

                        switch(pilKursi){
                            case 1:
                                crudKursi.tambahKursi();
                                break;
                            case 2:
                                crudKursi.tampilKursi();
                                break;
                            case 3:
                                crudKursi.ubahKursi();
                                break;
                            case 4:
                                crudKursi.hapusKursi();
                                break;
                            case 5:
                                program();
                                break;
                            default:
                                System.err.println("Pilihan tidak tersedia..!");
                                break;
                        }
                    } while (pilKursi != 5);
                break;
                case 5:
                    pemesanan.data_pemesanan();                 
                    break;
                case 6:
                    System.out.println("Logout berhasil..!");
                    System.out.println("");
                    login.program();
                    break;
                default:
                    System.err.println("Pilihan tidak tersedia !");
            }
        } while (pilihan != 6);
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
