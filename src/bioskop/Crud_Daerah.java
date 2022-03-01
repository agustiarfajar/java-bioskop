
package bioskop;
import java.sql.*;
import java.util.Scanner;
/**
 * 
 * @author Agustiar
 */
public class Crud_Daerah implements Crud {  
    Menu_Admin menu = new Menu_Admin();
    Koneksi koneksi = new Koneksi();
    
    Scanner key = new Scanner(System.in);
    
    private int id_daerah;
    private String namaDaerah;

    public int getId_daerah() {
        return id_daerah;
    }

    public void setId_daerah(int id_daerah) {
        this.id_daerah = id_daerah;
    }

    public String getNamaDaerah() {
        return namaDaerah;
    }

    public void setNamaDaerah(String namaDaerah) {
        this.namaDaerah = namaDaerah;
    }
    
    public void tambahDaerah(){
        System.out.println("===== Menambahkan Daerah =====");
        System.out.print("Masukkan Daerah : ");
        setNamaDaerah(key.nextLine());
        simpanDB();
        menu.konfirmasi();
    }
    
    public void tampilDaerah(){
        System.out.println("===== Menampilkan Daerah =====");
        tampilDB();
        menu.konfirmasi();
    }
    
    public void ubahDaerah(){
        System.out.println("===== Mengubah Daerah =====");
        tampilDB();
        updateDB();
        menu.konfirmasi();
    }
    
    public void hapusDaerah(){
        System.out.println("===== Menghapus Daerah =====");     
        tampilDB();
        hapusDB();
        menu.konfirmasi();
    }
    
    @Override
    public void simpanDB() {
        koneksi.koneksi();
        String query = "INSERT INTO daerah (nama) VALUES ('" + getNamaDaerah() + "')";
        try {
            PreparedStatement pst = Koneksi.conn.prepareStatement(query);
            pst.execute();
            System.out.println("Daerah " + getNamaDaerah() + " berhasil ditambahkan!");
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan daerah : " + e.getMessage());
        }
    }
    
    @Override
    public void tampilDB() {
        koneksi.koneksi();
        String query = "SELECT * FROM daerah";
        try {
            Statement st = Koneksi.conn.createStatement();          
            ResultSet rsDaerah = st.executeQuery(query);
            while (rsDaerah.next()) {
                int id_daerahDB = rsDaerah.getInt("id_daerah");
                String nama = rsDaerah.getString("nama");
                System.out.format("%s.| %s\n", id_daerahDB, nama);
            }
        } catch (SQLException e) {
            System.err.println("Gagal melihat daerah : " + e.getMessage());
        }
    }
    
    @Override
    public void updateDB () {
        koneksi.koneksi();
        try {
            Statement st = Koneksi.conn.createStatement();
            int pilihDaerah;
            int id_daerahDB = 0;
            String queryDaerah;
            ResultSet rsDaerah;
            do { 
                System.out.print("Pilih No.Daerah Untuk Diubah : ");
                
                pilihDaerah = key.nextInt();
                queryDaerah = "select id_daerah, nama from daerah where id_daerah = "+ pilihDaerah +"";
                rsDaerah = st.executeQuery(queryDaerah);
                if (rsDaerah.next()) {
                    id_daerahDB = rsDaerah.getInt("id_daerah");
                 
                    setId_daerah(id_daerahDB);
                    String updateNama;
                    System.out.print("Nama Daerah : ");
                    key.nextLine();
                    updateNama = key.nextLine();
                    setNamaDaerah(updateNama);

                    String query = "UPDATE daerah SET nama = '"+ getNamaDaerah() +"' WHERE id_daerah = "+ getId_daerah() +"";
                    PreparedStatement pst = Koneksi.conn.prepareStatement(query);
                    pst.execute();
                    System.out.println("Daerah berhasil diubah!");
                } else  {
                    System.err.println("Daerah Tidak Tersedia!");
                }
            } while (id_daerahDB != pilihDaerah || id_daerahDB < 1);
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan daerah : " + e.getMessage());
        }
    }
    
    @Override
    public void hapusDB() {
        koneksi.koneksi();
        try {
            int pilihDaerah;
            int id_daerahDB = 0;
            char konfirmasi_hapus;
            String queryDaerah;
            ResultSet rsDaerah;
            Statement st = Koneksi.conn.createStatement();
            do { 
                System.out.print("Pilih No.Daerah Untuk Dihapus : ");
                
                pilihDaerah = key.nextInt();
                queryDaerah = "select id_daerah, nama from daerah where id_daerah = "+ pilihDaerah +"";
                
                rsDaerah = st.executeQuery(queryDaerah);
                if (rsDaerah.next()) {
                    id_daerahDB = rsDaerah.getInt("id_daerah");    
                    setId_daerah(id_daerahDB);
                    System.out.print("Apakah anda yakin ingin menghapus data [Y/T] : ");
                    konfirmasi_hapus = key.next().charAt(0);
                    konfirmasi_hapus = Character.toUpperCase(konfirmasi_hapus);
                    if (konfirmasi_hapus == 'Y') {
                        String query = "DELETE from daerah WHERE id_daerah = '"+ getId_daerah() +"'";
                        PreparedStatement pst = Koneksi.conn.prepareStatement(query);
                        pst.execute();
                        System.out.println("Daerah berhasil dihapus!");
                    } else if(konfirmasi_hapus == 'T') {
                        System.out.println("Daerah batal dihapus");
                    }
                } else  {
                    System.err.println("Daerah Tidak Tersedia!");
                }
            } while (id_daerahDB != pilihDaerah || id_daerahDB < 1);
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan daerah : " + e.getMessage());
        }
    }
}
