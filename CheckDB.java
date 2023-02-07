import java.sql.*;
/**
 * Write a description of class CheckDB here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CheckDB
{
    public static void main(String[]args) throws Exception
    { 
        Class.forName("org.sqlite.JDBC");
        Connection co = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\robi\\Documents\\Sqlite\\perpus.db");
        Statement s = co.createStatement(); //mengeksekusi pernyataan SQL
        ResultSet rs = s.executeQuery("select * from user");
        // tabel virtual yg mewakili data hasil eksekusi
        while(rs.next()){
            System.out.println("NPM : "+rs.getString("npm"));
            System.out.println("Nama : "+rs.getString("nama"));
            System.out.println("Jurusan : "+rs.getString("jurusan"));
            System.out.println("No Telp : "+rs.getString("no_telp"));
            System.out.println("Email : "+rs.getString("email"));
            System.out.println("");
         }
       
        rs.close();
        co.close();
     
    }
}
