
package bioskop;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author admin
 */
public class Crud_Kursi implements Crud {
    
    Menu_Admin menu = new Menu_Admin();
    Koneksi koneksi = new Koneksi();
    
    Scanner input = new Scanner(System.in);
    
    private int id_kursi;
    private String noKursi;
    private int status;
    private int idStudio;
    

    public int getId_kursi() {
        return id_kursi;
    }

    public void setId_kursi(int id_kursi) {
        this.id_kursi = id_kursi;
    }
    
    public String getNoKursi() {
        return noKursi;
    }

    public void setNoKursi(String noKursi) {
        this.noKursi = noKursi;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(int idStudio) {
        this.idStudio = idStudio;
    }
    
    public void tambahKursi(){
        System.out.println("===== Menambahkan Kursi =====");
        simpanDB();
        menu.konfirmasi();
    }
    
    public void tampilKursi(){
        System.out.println("===== Menampilkan Kursi =====");
        tampilDB();
        menu.konfirmasi();
    }
    
    public void ubahKursi(){
        System.out.println("===== Mengubah Kursi =====");
        tampilDB();
        updateDB();
        menu.konfirmasi();
    }
    
    public void hapusKursi(){
        System.out.println("===== Menghapus Kursi =====");
        tampilDB();
        hapusDB();
        menu.konfirmasi();
    }

    @Override
    public void simpanDB() {
        
        int id_studio = 0;
        int pilihStudio;
        koneksi.koneksi();
        try {
            Statement st = Koneksi.conn.createStatement();
            System.out.println("Studio yang Tersedia");
            System.out.println("===================");
            Crud_Studio studio = new Crud_Studio();
            studio.tampilDB();
            System.out.println("===================");
            do {
                System.out.print("Pilih Studio : ");
                pilihStudio = input.nextInt();
                String queryStudio = "SELECT * FROM studio WHERE id_studio = '"+ pilihStudio +"'";
                ResultSet rsStudio = st.executeQuery(queryStudio);
                if (rsStudio.next()) {
                    id_studio = rsStudio.getInt("id_studio");
                    setIdStudio(id_studio);
                    
                    System.out.print("Masukkan No. Kursi : ");
                    input.nextLine();
                    setNoKursi(input.nextLine());
                    System.out.print("Masukkan Status Kursi : ");
                    setStatus(input.nextInt());
                    String query = "INSERT INTO Kursi (no_kursi, status, id_studio)"
                                    +" VALUES ('"+ getNoKursi() +"', '"+ getStatus() +"', '"+ getIdStudio() +"')";
                    PreparedStatement pst = Koneksi.conn.prepareStatement(query);
                    pst.execute();
                    System.out.println("No Kursi "+getNoKursi()+" berhasil ditambahkan");
                } else {
                    System.err.println("Kursi Tidak Tersedia!");
                }
            } while (id_studio != pilihStudio || id_studio < 1);
            
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
            
            String query = "SELECT kursi.id_kursi, kursi.no_kursi, kursi.status, studio.nama FROM kursi INNER JOIN studio ON kursi.id_studio = studio.id_studio";
            ResultSet rsKursi = st.executeQuery(query);
            
            while (rsKursi.next()){
                int idKursi = rsKursi.getInt("kursi.id_kursi");
                String no_kursi = rsKursi.getString("no_kursi");
                int status_kursi = rsKursi.getInt("status");
                String nama_studio = rsKursi.getString("nama");
                System.out.format("%s. | %s | %s | %s\n", idKursi, no_kursi, status_kursi, nama_studio);
            }
        } catch (SQLException e){
            System.err.println("Error : "+e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void updateDB() {
        koneksi.koneksi();
        try {
            Statement st = Koneksi.conn.createStatement();
            
            int pilihKursi;
            int idKursi = 0;
            do {
                System.out.print("Pilih No. Kursi yang Ingin Diubah : ");
                pilihKursi = input.nextInt();
                String queryKursi = "SELECT * FROM kursi WHERE id_kursi = '"+pilihKursi+"'";
                ResultSet rsKursi = st.executeQuery(queryKursi);
                
                if (rsKursi.next()) {
                    idKursi = rsKursi.getInt("id_kursi");
                    setId_kursi(idKursi);
                    
                    String updateNoKursi;
                    int updateStatus;
                    
                    System.out.print("Masukkan No. Kursi : ");
                    input.nextLine();
                    updateNoKursi = input.nextLine();
                    System.out.print("Masukkan Status Kursi : ");
                    updateStatus = input.nextInt();
                    
                    setNoKursi(updateNoKursi);
                    setStatus(updateStatus);
                    
                    String queryUpdate = "UPDATE kursi SET no_kursi = '"+ getNoKursi() +"', status = '"+ getStatus() +"' WHERE id_kursi = '"+ getId_kursi() +"'";
                    PreparedStatement pst = Koneksi.conn.prepareStatement(queryUpdate);
                    pst.execute();
                    System.out.println("No. Kursi berhasil diubah!");
                } else {
                    System.err.println("No. Kursi tidak tersedia");
                }
            } while (idKursi != pilihKursi || idKursi < 1);
        } catch (SQLException e) {
            System.err.println("Error : "+e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void hapusDB() {
        koneksi.koneksi();
        try {
            Statement st = Koneksi.conn.createStatement();
            char konfirmasi_hapus;
            int pilihKursi;
            int idKursi = 0;
            do {
                System.out.print("Pilih No. Kursi yang Ingin Dihapus : ");
                pilihKursi = input.nextInt();
                String queryKursi = "SELECT * FROM kursi WHERE id_kursi = '"+pilihKursi+"'";
                ResultSet rsKursi = st.executeQuery(queryKursi);
                if (rsKursi.next()) {
                    idKursi = rsKursi.getInt("id_kursi");
                    setId_kursi(idKursi);
                    System.out.print("Apakah anda yakin ingin menghapus data [Y/T] : ");
                    konfirmasi_hapus = input.next().charAt(0);
                    konfirmasi_hapus = Character.toUpperCase(konfirmasi_hapus);
                    if (konfirmasi_hapus == 'Y') {
                        String queryHapus = "DELETE FROM kursi WHERE id_kursi = '"+getId_kursi()+"'";
                        PreparedStatement pst = Koneksi.conn.prepareStatement(queryHapus);
                        pst.execute();
                        System.out.println("Kursi berhasil dihapus!");
                    } else if(konfirmasi_hapus == 'T') {
                        System.out.println("Daerah batal dihapus");
                    }
                } else {
                    System.err.println("Kursi tidak ditemukan");
                }
            } while (idKursi != pilihKursi || idKursi < 1);
        } catch (SQLException e) {
            System.err.println("Error : "+e.getMessage());
            System.exit(0);
        }
    }
}
