/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop;
import java.sql.*;

/**
 *
 * @author Fajar
 */
public class Koneksi {
    static final String DB = "jdbc:mysql://localhost/bioskop";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn;
    
    public void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi gagal : " + e.getMessage());
        }
    }
}
