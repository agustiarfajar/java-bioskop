/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop;
import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Pemesanan {
    private int id_film;
    private String judul, kategori;
    private String jamTayang;
    private Double harga;
    private int id_daerah;
    private String namaDaerah;
    private int id_studio;
    private String namaStudio;
    private int id_kursi;
    private String no_kursi;
    private int id_user;
    private String namaUser;
    private String no_telp;
    
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001b[30m";
    
    Scanner key = new Scanner(System.in);  

    Koneksi koneksi = new Koneksi();
    Menu_User menu_user = new Menu_User();
    Akun akun = new Akun();

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJamTayang() {
        return jamTayang;
    }

    public void setJamTayang(String jamTayang) {
        this.jamTayang = jamTayang;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

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

    public int getId_studio() {
        return id_studio;
    }

    public void setId_studio(int id_studio) {
        this.id_studio = id_studio;
    }

    public String getNamaStudio() {
        return namaStudio;
    }

    public void setNamaStudio(String namaStudio) {
        this.namaStudio = namaStudio;
    }

    public int getId_kursi() {
        return id_kursi;
    }

    public void setId_kursi(int id_kursi) {
        this.id_kursi = id_kursi;
    }

    public String getNo_kursi() {
        return no_kursi;
    }

    public void setNo_kursi(String no_kursi) {
        this.no_kursi = no_kursi;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }
    
    public void pemesanan() {
        koneksi.koneksi();
        System.out.println("");
        System.out.println("======================================");
        String query = "select * from film";
        try { 
            Statement st = Koneksi.conn.createStatement();
            ResultSet rsFilm = st.executeQuery(query);

            System.out.format("%s| %s\n", "NO.", "JUDUL FILM");
            System.out.println("------------------------------");
            while (rsFilm.next()) {

                int id_film_db = rsFilm.getInt("id_film");
                String judul_db = rsFilm.getString("judul");

                // menampilkan data
                System.out.format("%s  | %s\n", id_film_db, judul_db);
            }
            
            int pilihFilm;
            int id_film_db = 0;
            String queryDetailFilm;
            ResultSet rsDetailFilm;
            do {
                System.out.println("------------------------------");
                System.out.print("Pilih Film berdasarkan nomer : "); 
                pilihFilm = key.nextInt();

                queryDetailFilm = "select * from film where id_film = "+pilihFilm+"";
                rsDetailFilm = st.executeQuery(queryDetailFilm); 

                if (rsDetailFilm.next()) {           
                    id_film_db = rsDetailFilm.getInt("id_film");
                    String detailJudul = rsDetailFilm.getString("judul");
                    String kategori_db = rsDetailFilm.getString("kategori");
                    Double harga_db = rsDetailFilm.getDouble("harga");
                    setId_film(id_film_db);
                    setJudul(detailJudul);
                    setKategori(kategori_db);
                    setHarga(harga_db);

                    System.out.println("Pilihan Film\t: " + detailJudul);
                    System.out.println("Kategori Film\t: " + kategori);
                } else {
                    System.err.println("Film Tidak Tersedia");
                }
            } while (id_film_db != pilihFilm || id_film_db < 1);
 
            System.out.println("");
            String queryJamTayang = "select jam_tayang_1, jam_tayang_2, jam_tayang_3 from film where id_film = "+pilihFilm+"";
            ResultSet rsJamTayang = st.executeQuery(queryJamTayang);

            while(rsJamTayang.next()) {
                String jamTayang1 = rsJamTayang.getString("jam_tayang_1");
                String jamTayang2 = rsJamTayang.getString("jam_tayang_2");
                String jamTayang3 = rsJamTayang.getString("jam_tayang_3");

                System.out.println("Jam Tayang Film : ");
                System.out.println("1. " + jamTayang1);
                System.out.println("2. " + jamTayang2);
                System.out.println("3. " + jamTayang3);
                
                int pilihJamTayang;
                do {
                    System.out.println("------------------------------");
                    System.out.print("Pilih Jam Tayang      : ");     
                    pilihJamTayang = key.nextInt();
                    switch (pilihJamTayang) {
                        case 1:
                            setJamTayang(jamTayang1);
                            break;
                        case 2:
                            setJamTayang(jamTayang2);
                            break;
                        case 3:
                            setJamTayang(jamTayang3);
                            break;
                        default:
                            System.err.println("Jam tayang tidak tersedia");
                            break;
                    }
                } while (pilihJamTayang < 1 || pilihJamTayang > 3);

                System.out.println(" Pilihan jam tayang : "+ getJamTayang()); 
            }

            System.out.println("");
            System.out.println("Daerah Yang Tersedia");
            String queryDaerah = "select * from daerah";
            ResultSet rsDaerah = st.executeQuery(queryDaerah);

            while (rsDaerah.next()) {
                int id_daerah_db = rsDaerah.getInt("id_daerah");
                String daerah_db = rsDaerah.getString("nama");
                System.out.format("%s. %s\n", id_daerah_db, daerah_db);
            }
          
            int pilihDaerah;
            String detailDaerah;
            ResultSet rsDetailDaerah;
            int id_daerah_db = 0;
            do {
                System.out.println("------------------------------");
                System.out.print("Pilih Daerah      : ");
                pilihDaerah = key.nextInt();

                detailDaerah = "select id_daerah, nama from daerah where id_daerah = "+ pilihDaerah +"";
                rsDetailDaerah = st.executeQuery(detailDaerah);
                if (rsDetailDaerah.next()) {
                    id_daerah_db = rsDetailDaerah.getInt("id_daerah");
                    String namaDaerah_db = rsDetailDaerah.getString("nama");
                    setId_daerah(id_daerah_db);
                    setNamaDaerah(namaDaerah_db);
                } else {
                    System.err.println("Daerah tidak tersedia!");
                }
            } while (id_daerah_db != pilihDaerah || id_daerah_db < 1);
            
            System.out.println(" Pilihan Daerah : "+ getNamaDaerah());
            System.out.println("");

            System.out.println("Studio Yang Tersedia : ");
            String queryStudio = "select * from studio where id_daerah = "+ pilihDaerah +"";
            ResultSet rsStudio = st.executeQuery(queryStudio);
            while (rsStudio.next()) {
                int id_studio_db = rsStudio.getInt("id_studio");
                String studio_db = rsStudio.getString("nama");
                System.out.format("%s. %s\n",id_studio_db, studio_db);
            }    

            
            int pilihStudio;
            String detailStudio;
            ResultSet rsDetailStudio;
            int id_studio_db = 0;
            
            do {
                System.out.println("------------------------------");
                System.out.print("Pilih Studio      : ");
                pilihStudio = key.nextInt();
                detailStudio = "select studio.id_studio, studio.nama from studio "
                        + "INNER JOIN daerah ON studio.id_daerah = daerah.id_daerah where id_studio = "+ pilihStudio +" AND daerah.id_daerah = "+ pilihDaerah +"";
                rsDetailStudio = st.executeQuery(detailStudio);
                if (rsDetailStudio.next()) {
                    id_studio_db = rsDetailStudio.getInt("id_studio");
                    String namaStudio_db = rsDetailStudio.getString("nama");
                    setId_studio(id_studio_db);
                    setNamaStudio(namaStudio_db);
                } else {
                    System.err.println("Studio tidak tersedia");
                }
            } while (id_studio_db != pilihStudio || id_studio_db < 1);
            
            System.out.println(" Pilihan Studio : " + getNamaStudio());
            System.out.println("");             
            System.out.println("Kursi Yang Tersedia : ");
            
            String queryJumlahKursi = "select COUNT(*) AS jumlahKursi from kursi where id_studio = "+ pilihStudio +" AND kursi.status = 1";
            ResultSet rsJumlahKursi = st.executeQuery(queryJumlahKursi);
            
            if (rsJumlahKursi.next()) {
                if (rsJumlahKursi.getInt("jumlahKursi") > 0) {
                    String queryKursi = "select * from kursi where id_studio = "+ pilihStudio +" AND kursi.status = 1";
                    ResultSet rsKursi = st.executeQuery(queryKursi);
                    while (rsKursi.next()) {
                        int id_kursi_db = rsKursi.getInt("id_kursi");
                         String no_kursi_db = rsKursi.getString("no_kursi");
                         System.out.format("%s. %s\n", id_kursi_db, no_kursi_db);
                     }
                } else {
                    System.err.println("Kursi Kosong!");
                    System.exit(0);
                } 
            } 
            
            int pilihKursi;
            int id_kursi_db = 0;
            String queryTiketKursi;
            ResultSet rsTiketKursi;

            do { 
                System.out.println("------------------------------");
                System.out.print("Pilih Kursi      : ");
                pilihKursi = key.nextInt();
                queryTiketKursi = "select kursi.id_kursi, kursi.no_kursi, kursi.status from kursi "
                        + "INNER JOIN studio ON studio.id_studio = kursi.id_studio WHERE kursi.id_kursi = "+ pilihKursi +" "
                        + " AND studio.id_studio = "+ pilihStudio +" AND kursi.status = 1";
                rsTiketKursi = st.executeQuery(queryTiketKursi);
                if (rsTiketKursi.next()) {
                    id_kursi_db = rsTiketKursi.getInt("id_kursi");
                    String no_kursi_db = rsTiketKursi.getString("no_kursi");
                    setId_kursi(id_kursi_db);
                    setNo_kursi(no_kursi_db);
                } else  {
                    System.err.println("Kursi Tidak Tersedia!");
                }
            } while (id_kursi_db != pilihKursi || id_kursi_db < 1);
                   
            System.out.println("Pilihan Kursi : " + getNo_kursi());
            System.out.println("");

            System.out.println(CYAN+"==============================================");
            System.out.print("Anda yakin ingin memesan tiket [Y/T]: ");
            char pilih;
            pilih = key.next().charAt(0);
            switch (Character.toUpperCase(pilih)) {
                case 'Y':
                    try {
                        String sql = "INSERT INTO pemesanan(id_user, id_film, id_kursi, id_studio, jam_tayang, total) values('"+ getId_user() +"', '"+ getId_film() +"', '"+ getId_kursi() +"', '"+ getId_studio() +"', '"+ getJamTayang() +"', '"+ getHarga() +"')";
                        String updateSql = "UPDATE kursi SET status = 0 WHERE id_kursi = '"+ getId_kursi() +"'";
                        PreparedStatement pst = Koneksi.conn.prepareStatement(sql);
                        PreparedStatement pstUpdate = Koneksi.conn.prepareStatement(updateSql);
                        pst.execute();
                        pstUpdate.execute();
                        System.out.println("Pemesanan tiket telah berhasil. Silahkan lihat tiket di MyTiket");
                        menu_user.setId_user(getId_user());
                        menu_user.konfirmasi();
                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 'T':
                    System.exit(0);
                default:
                    System.err.println("Perintah Tidak Tersedia!");
                    break;
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
        }catch (InputMismatchException ex) {
            System.err.println("Masukan hanya berupa angka!");
            System.exit(0);
        } 
    }
    
    public void myTiket() {
        koneksi.koneksi();
        String query = "SELECT user.id_user, user.nama as nama_user, user.no_telp, film.judul, film.kategori, daerah.nama as nama_daerah, studio.nama as nama_studio, kursi.no_kursi, pemesanan.jam_tayang, pemesanan.total "
                + "FROM pemesanan "
                + "INNER JOIN user ON pemesanan.id_user = user.id_user "  
                + "INNER JOIN film ON pemesanan.id_film = film.id_film "
                + "INNER JOIN kursi ON pemesanan.id_kursi = kursi.id_kursi "
                + "INNER JOIN studio ON pemesanan.id_studio = studio.id_studio "
                + "INNER JOIN daerah ON daerah.id_daerah = studio.id_daerah "
                + "WHERE pemesanan.id_user = '"+ getId_user() +"'";
        try {
            Statement st = Koneksi.conn.createStatement();
            ResultSet rsPemesanan = st.executeQuery(query);
            System.out.println(CYAN+"================="+BLACK+" TIKET SAYA "+CYAN+"===================");
            while (rsPemesanan.next()) {
                int id_user_db = rsPemesanan.getInt("id_user");
                String nama_db = rsPemesanan.getString("nama_user");
                String no_telp_db = rsPemesanan.getString("no_telp");
                String judul_db = rsPemesanan.getString("judul");
                String kategori_db = rsPemesanan.getString("kategori");
                String nama_daerah_db = rsPemesanan.getString("nama_daerah");
                String nama_studio_db = rsPemesanan.getString("nama_studio");
                String no_kursi_db = rsPemesanan.getString("no_kursi");
                String jam_tayang_db = rsPemesanan.getString("jam_tayang");
                String total_db = rsPemesanan.getString("total");
               
                System.out.println("ID Pemesan \t: " + id_user_db);
                System.out.println("Nama Pemesan \t: " + nama_db);
                System.out.println("No. Telepon\t: " + no_telp_db);
                System.out.println("----------------------------------------------");
                System.out.println("Film \t\t: " + judul_db);
                System.out.println("Kategori\t: " + kategori_db);
                System.out.println("Jam Tayang\t: "+ jam_tayang_db);
                System.out.println("Harga \t\t: " + total_db);
                System.out.println("----------------------------------------------");
                System.out.println("Studio \t\t: " + nama_studio_db);
                System.out.println("Daerah \t\t: " + nama_daerah_db);          
                System.out.println("No.Kursi \t: " + no_kursi_db);
                System.out.println(CYAN+"==============================================");
                System.out.println("");
            }
            menu_user.setId_user(getId_user());
            menu_user.konfirmasi();
        } catch (SQLException e) {
            System.out.println("Gagal!" + e.getMessage());
        }      
    }
    
    public void data_pemesanan() {
        koneksi.koneksi();
        int i = 0;
        String query = "SELECT user.id_user, user.nama as nama_user, user.no_telp, film.judul, film.kategori, "
                + "daerah.nama as nama_daerah, studio.nama as nama_studio, kursi.no_kursi, pemesanan.jam_tayang, "
                + "pemesanan.total "
                + "FROM pemesanan "
                + "INNER JOIN user ON pemesanan.id_user = user.id_user "  
                + "INNER JOIN film ON pemesanan.id_film = film.id_film "
                + "INNER JOIN kursi ON pemesanan.id_kursi = kursi.id_kursi "
                + "INNER JOIN studio ON pemesanan.id_studio = studio.id_studio "
                + "INNER JOIN daerah ON daerah.id_daerah = studio.id_daerah ";
        try {
            Statement st = Koneksi.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("                            Data Pemesanan Tiket Bioskop                                   ");
            System.out.println("===========================================================================================");
            System.out.println("# | #ID User | #Nama User | #Film | #No Kursi | #Studio | #Daerah | #Jam Tayang | #Total |");
            System.out.println("-------------------------------------------------------------------------------------------");
            while (rs.next()) {
                i = i +1;
                int id_user_db = rs.getInt("id_user");
                String nama_user_db = rs.getString("nama_user");
                String judul_film_db = rs.getString("judul");
                String no_kursi_db = rs.getString("no_kursi");
                String studio_db = rs.getString("nama_studio");
                String daerah_db = rs.getString("nama_daerah");
                String jam_tayang_db = rs.getString("jam_tayang");
                String total = rs.getString("total");
                
                System.out.format("%s | %s | %s | %s | %s | %s | %s | %s | Rp.%s\n", i, id_user_db, nama_user_db, judul_film_db, no_kursi_db, studio_db, daerah_db, jam_tayang_db, total);
            }
            menu_user.setId_user(getId_user());
            menu_user.konfirmasi();
        } catch (SQLException e) {
            System.out.println("Gagal!" + e.getMessage());
        }
    }
}
