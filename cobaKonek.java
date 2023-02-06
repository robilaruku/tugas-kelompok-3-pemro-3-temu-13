import java.sql.*;    // koneksi database
import java.io.*;     // input output
import javax.swing.*;  // mengkombinasi script lebih dari 1 dijadikan 1 form 

/**
 * Write a description of class cobaKonek here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class cobaKonek
{
    public static void lihat()
    {
        try{
            //load database
            Class.forName("org.sqlite.JDBC");

            //buat koneksi
            Connection koneksi=DriverManager.getConnection("JDBC:sqlite:C:\\Users\\robi\\Documents\\Sqlite\\warehouse.db");
            //buat statement
            Statement stm=koneksi.createStatement();
            
            ResultSet rs=stm.executeQuery("select * from barang"); 
            
            //ambil MetaData
            ResultSetMetaData rsmd=rs.getMetaData();
            int kolom=rsmd.getColumnCount();
            
            //tampilkan judul kolom
            for(int i=1;i<=kolom;i++)
            {
                System.out.print(rsmd.getColumnName(i)+" | ");
            }
            System.out.println("\n");
            while(rs.next())
            {
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
            }
        }
        catch(Exception e){
            System.err.println("Eror"+e.getMessage());
        }
   }
   
   public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:warehouse.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        lihat();
    }
}
