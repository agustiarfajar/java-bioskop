
package bioskop;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Hanung
 */
public class Crud_Studio implements Crud {
    
    private int idStudio;
    private String namaStudio;
    private int idDaerah;

    Scanner key = new Scanner(System.in); 
    Koneksi koneksi = new Koneksi();
    Menu_Admin menu = new Menu_Admin();
  
    public int getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(int idStudio) {
        this.idStudio = idStudio;
    }
    
    public String getNamaStudio() {
        return namaStudio;
    }

    public void setNamaStudio(String namaStudio) {
        this.namaStudio = namaStudio;
    }

    public int getIdDaerah() {
        return idDaerah;
    }

    public void setIdDaerah(int idDaerah) {
        this.idDaerah = idDaerah;
    }
    
    public void tambahStudio(){
        System.out.println("===== Menambahkan Studio =====");
        simpanDB();
        menu.konfirmasi();
    }
    
    public void tampilStudio(){
        System.out.println("===== Menampilkan Studio =====");
        tampilDB();
        menu.konfirmasi();
    }
    
    public void ubahStudio(){
        System.out.println("===== Mengubah Studio =====");
        tampilDB();
        updateDB();
        menu.konfirmasi();
    }
    
    public void hapusStudio(){
        System.out.println("===== Menghapus Studio =====");
        tampilDB();
        hapusDB();
        menu.konfirmasi();
    }
    
    @Override
    public void simpanDB() {
        int id_daerah = 0;
        int pilihDaerah;
        koneksi.koneksi();
        try {
            Statement st = Koneksi.conn.createStatement();
            System.out.println("Daerah Yang Tersedia");
            System.out.println("=================");
            Crud_Daerah daerah = new Crud_Daerah();
            daerah.tampilDB();
            System.out.println("=================");
            do {
                System.out.print("Pilih Daerah : ");
                pilihDaerah = key.nextInt();
                String queryDaerah = "SELECT * FROM daerah WHERE id_daerah = '"+ pilihDaerah +"'";
                ResultSet rsDaerah = st.executeQuery(queryDaerah);
                if (rsDaerah.next()) {
                    id_daerah = rsDaerah.getInt("id_daerah");
                    setIdDaerah(id_daerah);
                    
                    System.out.print("Masukan Nama Studio : ");
                    key.nextLine();
                    setNamaStudio(key.nextLine());
                    String query = "INSERT INTO studio (nama, id_daerah) VALUES ('"+ getNamaStudio() +"', '"+ getIdDaerah() +"')";
                    PreparedStatement pst = Koneksi.conn.prepareStatement(query);
                    pst.execute();
                    System.out.println("Studio " + getNamaStudio() + " berhasil ditambahkan!");
                } else {
                    System.err.println("Daerah Tidak Tersedia!");
                }
            } while (id_daerah != pilihDaerah || id_daerah < 1);
            
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
           
           String query = "SELECT studio.id_studio, studio.nama, daerah.nama FROM studio INNER JOIN daerah ON studio.id_daerah = daerah.id_daerah";
           ResultSet rsStudio = st.executeQuery(query);
           
           while (rsStudio.next()) {
               int id_studio = rsStudio.getInt("studio.id_studio");
               String nama_studio = rsStudio.getString("studio.nama");
               String nama_daerah = rsStudio.getString("daerah.nama");
               System.out.format("%s.| %s | %s\n", id_studio, nama_studio, nama_daerah);
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
            
            int pilihStudio;
            int id_studio = 0;
            do {
                System.out.print("Pilih No.Studio Yang Ingin Diubah : ");
                pilihStudio = key.nextInt();
                String query = "SELECT * FROM studio WHERE id_studio = '"+ pilihStudio +"'";
                ResultSet rsStudio = st.executeQuery(query);
                
                if (rsStudio.next()) {
                    id_studio = rsStudio.getInt("id_studio");
                    String updateStudio;
                    System.out.print("Nama Studio : ");
                    key.nextLine();
                    updateStudio = key.nextLine();
                    
                    setIdStudio(id_studio);
                    setNamaStudio(updateStudio);
                    
                    String queryUpdate = "UPDATE studio SET nama = '"+ getNamaStudio() +"' WHERE id_studio = '"+ getIdStudio() +"'";
                    PreparedStatement pst = Koneksi.conn.prepareStatement(queryUpdate);
                    pst.execute();
                    System.out.println("Data Studio Berhasil Diubah!");
                } else {
                    System.err.println("Studio tidak tersedia!");
                }
            } while (id_studio != pilihStudio || id_studio < 1);
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
            int pilihStudio;
            int id_studio = 0;
            do {
                System.out.print("Pilih No.Studio Yang Ingin Dihapus : ");
                pilihStudio = key.nextInt();
                String query = "SELECT * FROM studio WHERE id_studio = '"+ pilihStudio +"'";
                ResultSet rsStudio = st.executeQuery(query);
                
                if (rsStudio.next()) {
                    id_studio = rsStudio.getInt("id_studio");     
                    setIdStudio(id_studio);
                    System.out.print("Apakah anda yakin ingin menghapus data [Y/T] : ");
                    konfirmasi_hapus = key.next().charAt(0);
                    konfirmasi_hapus = Character.toUpperCase(konfirmasi_hapus);
                    if (konfirmasi_hapus == 'Y') {
                        String queryHapus = "DELETE FROM studio WHERE id_studio = '"+ getIdStudio() +"'";
                        PreparedStatement pst = Koneksi.conn.prepareStatement(queryHapus);
                        pst.execute();
                        System.out.println("Data Studio Berhasil Dihapus!");
                    } else if(konfirmasi_hapus == 'T') {
                        System.out.println("Daerah batal dihapus");
                    }
                } else {
                    System.err.println("Studio tidak tersedia!");
                }
            } while (id_studio != pilihStudio || id_studio < 1);
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
            System.exit(0);
        }
    }
}
