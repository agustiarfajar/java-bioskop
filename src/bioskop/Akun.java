/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop;
import java.util.Scanner;
import java.sql.*;
import java.util.InputMismatchException;
/**
 *
 * @author Fajar
 */
public class Akun {
    Koneksi koneksi = new Koneksi();
    Scanner key = new Scanner(System.in);
    Menu_Admin menu_admin = new Menu_Admin();
    Menu_User menu_user = new Menu_User();
    Menu_Login menu_login = new Menu_Login();
    
    private int idUser;
    private String nama;
    private String username;
    private String password;
    private String email;
    private String notelp;  
    private int hakAkses;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    private void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getNotelp() {
        return notelp;
    }
    
    private void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public int getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(int hakAkses) {
        this.hakAkses = hakAkses;
    }

    public void daftar_akun() {
        try {
            int hak_akses;
            String no_telp;
            String emaill;
            
            System.out.println("===== DAFTAR AKUN =====");
            System.out.print("Masukan Nama Lengkap\t: ");
            setNama(key.nextLine());
            System.out.print("Masukan Username\t: ");
            setUsername(key.next());
            System.out.print("Masukkan Password\t: ");
            setPassword(key.next());
            do {
                System.out.print("Masukkan Email\t\t: ");
                emaill = key.next();
                if (emaill.contains("@")) {
                    setEmail(emaill);
                } else {
                    System.err.println("Masukan format email salah, contoh: aaa@bbb.com..!");
                }
            } while (emaill.contains("@") == false);
            
            do {
                System.out.print("Masukan Hak Akses [1/2]\t: ");
                hak_akses = key.nextInt();
                if (hak_akses > 2 || hak_akses < 1) {
                    System.err.println("Pilihan tidak tersedia!");
                } else {
                    setHakAkses(hak_akses);
                }
            } while (hak_akses > 2 || hak_akses < 1);
            do {
                System.out.print("Masukan No Telepon\t: ");
                no_telp = key.next();
                if (no_telp.length() > 15) {
                    System.err.println("Panjang No.Telepon lebih dari 15 huruf..!");
                }
                setNotelp(no_telp); 
            } while (no_telp.length() > 15);
            Register();       
        } catch (InputMismatchException e) {
            System.err.println("Inputan tidak sesuai format!");
            System.exit(0);
        }
        konfirmasi();
    }
    
    public void masuk() {
        System.out.print("Masukan Username  : ");
        setUsername(key.next());
        System.out.print("Masukkan Password : ");
        setPassword(key.next());
        Login();
    }
    
    public void Register() {
        koneksi.koneksi();
        String query = "INSERT INTO user(nama, username, password, hak_akses, no_telp) VALUES('"+ getNama() +"', '"+ getUsername() +"', '"+ getPassword()+"', '"+ getHakAkses()+"', '"+ getNotelp() +"')";
        try {
            PreparedStatement pst = Koneksi.conn.prepareStatement(query);
            pst.execute();
            System.out.println("Akun berhasil didaftarkan!");
            
        } catch (SQLException e) {
            System.err.println("Akun gagal didaftarkan!");
        }
    }
    
    public void Login() {
        koneksi.koneksi();
        String query = "SELECT * FROM user WHERE username = '"+ getUsername() +"' AND password = '"+ getPassword() +"'";
        try {
            Statement st = Koneksi.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int id_user = rs.getInt("id_user");
                int hak_akses = rs.getInt("hak_akses");
                setIdUser(id_user);
                
                switch (hak_akses) {
                    case 1:
                        menu_admin.setId_user(id_user);
                        menu_admin.program();
                        break;
                    case 2:
                        menu_user.setId_user(id_user);
                        menu_user.program();
                        break;
                    default:
                        System.out.println("tes");
                        break;
                }
            } else {
                System.err.println("Username / Password salah !");
            }        
        } catch (SQLException e) {
            System.err.println("Login gagal!" + e.getMessage());
        }
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
                    menu_login.program();
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
