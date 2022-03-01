
package bioskop;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Hanung Dwi Prasetyo
 */
public class Crud_Film implements Crud {
    Koneksi koneksi = new Koneksi();
    Menu_Admin menu = new Menu_Admin();
    
    Scanner input = new Scanner(System.in);
    
    private int idFilm;
    private String judulFilm;
    private String kategori;
    private String jamTayang1;
    private String jamTayang2;
    private String jamTayang3;
    private Double harga;

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }
    
    public String getJudulFilm() {
        return judulFilm;
    }

    public void setJudulFilm(String judulFilm) {
        this.judulFilm = judulFilm;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJamTayang1() {
        return jamTayang1;
    }

    public void setJamTayang1(String jamTayang1) {
        this.jamTayang1 = jamTayang1;
    }

    public String getJamTayang2() {
        return jamTayang2;
    }

    public void setJamTayang2(String jamTayang2) {
        this.jamTayang2 = jamTayang2;
    }

    public String getJamTayang3() {
        return jamTayang3;
    }

    public void setJamTayang3(String jamTayang3) {
        this.jamTayang3 = jamTayang3;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }
  
    public void menambahkanFilm(){
        try {
            System.out.println("Silahkan Tambahkan Film yang akan Ditayangkan");
            System.out.println("=================================================");
            System.out.print("Masukkan Judul Film\t: ");
            setJudulFilm(input.nextLine());
            System.out.print("Masukkan Kategori Film\t: ");
            setKategori(input.nextLine());
            System.out.print("Masukkan Jam Tayang 1\t: ");
            setJamTayang1(input.nextLine());
            System.out.print("Masukkan Jam Tayang 2\t: ");
            setJamTayang2(input.nextLine());
            System.out.print("Masukkan Jam Tayang 3\t: ");
            setJamTayang3(input.nextLine());
            System.out.print("Masukkan Harga Film\t: ");
            setHarga(input.nextDouble());
            simpanDB();
        } catch (InputMismatchException e) {
            System.out.println("Inputan HARGA harus berupa angka!");
            System.out.println("Penyimpanan ke database gagal");
        }
        menu.konfirmasi();
    }
    
    public void menampilkanFilm(){
        System.out.println("Daftar Film yang sedang ditayangkan");
        System.out.println("=================================================");
        tampilDB();
        menu.konfirmasi();
    }
    
    public void mengeditFilm(){
        System.out.println("===== Mengubah Film =====");
        tampilDB();
        updateDB();
        menu.konfirmasi();
    }
    
    public void menghapusFilm(){
        System.out.println("===== Menghapus Film =====");
        tampilDB();
        hapusDB();
        menu.konfirmasi();  
    }

    @Override
    public void simpanDB() {
        koneksi.koneksi();
        try {
            String query = "INSERT INTO film (judul, kategori, jam_tayang_1, jam_tayang_2, jam_tayang_3, harga) VALUES "
                    + "('"+ getJudulFilm() +"', '"+ getKategori() +"', '"+ getJamTayang1() +"', '"+ getJamTayang2() +"', '"+ getJamTayang3() +"', '"+ getHarga()+"')";
            PreparedStatement pst = Koneksi.conn.prepareStatement(query);
            pst.execute();
            System.out.println("Film " + getJudulFilm() + " berhasil ditambahkan!");
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void tampilDB() {
        koneksi.koneksi();
        try {
           
           Statement st = Koneksi.conn.createStatement();
           
           String query = "SELECT * FROM film";
           ResultSet rsFilm = st.executeQuery(query);
           while (rsFilm.next()) {
                int id_film = rsFilm.getInt("id_film");
                String judul_film = rsFilm.getString("judul");
                String jam_tayang_1 = rsFilm.getString("jam_tayang_1");
                String jam_tayang_2 = rsFilm.getString("jam_tayang_2");
                String jam_tayang_3 = rsFilm.getString("jam_tayang_3");
                Double harga_film = rsFilm.getDouble("harga");
                
                System.out.format("%s.| %s | %s | %s | %s | Rp.%s\n", id_film, judul_film, jam_tayang_1, jam_tayang_2, jam_tayang_3, harga_film);
           }          
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void updateDB() {
        koneksi.koneksi();
        try { 
            Statement st = Koneksi.conn.createStatement();
            
            int pilihFilm;
            int id_film = 0;
            do {
                System.out.print("Pilih No.Film Yang Ingin Diubah : ");
                pilihFilm = input.nextInt();
                String queryFilm = "SELECT * FROM film WHERE id_film = '"+ pilihFilm +"'";
                ResultSet rsFilm = st.executeQuery(queryFilm);
                if (rsFilm.next()) {
                    id_film = rsFilm.getInt("id_film");
                    setIdFilm(id_film);
                    
                    String updateJudulFilm;
                    String updateKategoriFilm;
                    String updateJamTayangFilm1;
                    String updateJamTayangFilm2;
                    String updateJamTayangFilm3;
                    Double updateHargaFilm;
                    
                    System.out.print("Masukkan Judul Film : ");
                    input.nextLine();
                    updateJudulFilm = input.nextLine();
                    System.out.print("Masukkan Kategori Film : ");
                    updateKategoriFilm = input.nextLine();
                    System.out.print("Masukkan Jam Tayang 1 : ");
                    updateJamTayangFilm1 = input.nextLine();
                    System.out.print("Masukkan Jam Tayang 2 : ");
                    updateJamTayangFilm2 = input.nextLine();
                    System.out.print("Masukkan Jam Tayang 3 : ");
                    updateJamTayangFilm3 = input.nextLine();
                    System.out.print("Masukkan Harga Film : ");
                    updateHargaFilm = input.nextDouble();
                    
                    setJudulFilm(updateJudulFilm);
                    setKategori(updateKategoriFilm);
                    setJamTayang1(updateJamTayangFilm1);
                    setJamTayang2(updateJamTayangFilm2);
                    setJamTayang3(updateJamTayangFilm3);
                    setHarga(updateHargaFilm);
                                     
                    String queryUpdate = "UPDATE film SET judul = '"+ getJudulFilm() +"', kategori = '"+ getKategori() +"', "
                            + "jam_tayang_1 = '"+ getJamTayang1() +"', jam_tayang_2 = '"+ getJamTayang2() +"', jam_tayang_3 = '"+ getJamTayang3() +"', "
                            + "harga = '"+ getHarga() +"' WHERE id_film = '"+ getIdFilm() +"'";
                    PreparedStatement pst = Koneksi.conn.prepareStatement(queryUpdate);
                    pst.execute();
                    System.out.println("Film berhasil diubah!");
                } else {
                    System.err.println("Film Tidak Tersedia");
                }
            } while (id_film != pilihFilm || id_film < 1);
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
            System.exit(0); 
        }
    }

    @Override
    public void hapusDB() {
        koneksi.koneksi();
        try {
            Statement st = Koneksi.conn.createStatement();
            char konfirmasi_hapus;
            int pilihFilm;
            int id_film = 0;
            do {
                System.out.print("Pilih No.Film Yang Ingin Dihapus : ");
                pilihFilm = input.nextInt();
                String queryFilm = "SELECT * FROM film WHERE id_film = '"+ pilihFilm +"'";
                ResultSet rsFilm = st.executeQuery(queryFilm);
                if (rsFilm.next()) {
                    id_film = rsFilm.getInt("id_film");
                    setIdFilm(id_film);                   
                    System.out.print("Apakah anda yakin ingin menghapus data [Y/T] : ");
                    konfirmasi_hapus = input.next().charAt(0);
                    konfirmasi_hapus = Character.toUpperCase(konfirmasi_hapus);  
                    if (konfirmasi_hapus == 'Y') {
                        String queryHapus = "DELETE FROM film WHERE id_film = '"+ getIdFilm() +"'";
                        PreparedStatement pst = Koneksi.conn.prepareStatement(queryHapus);
                        pst.execute();
                        System.out.println("Film berhasil dihapus!");
                    } else if(konfirmasi_hapus == 'T') {
                        System.out.println("Daerah batal dihapus");
                    }
                } else {
                    System.err.println("Film Tidak Tersedia");
                }
            } while (id_film != pilihFilm || id_film < 1);
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
            System.exit(0); 
        }
    }
}
