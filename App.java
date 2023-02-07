import java.sql.*; // koneksi database
import java.io.*; // input output
/**
 * Write a description of class App here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class App {

  static final String JDBC_DRIVER = "org.sqlite.JDBC";
  static final String DB_URL = "jdbc:sqlite:C:\\Users\\robi\\Documents\\Sqlite\\perpus.db";

  static Connection conn;
  static Statement stm;
  static ResultSet rs;

  public static void lihat() {

    String sql = "SELECT * FROM user";

    try {

      rs = stm.executeQuery(sql);

      // ambil MetaData
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();
      int cellWidth = 20;

      // Print header
      for (int i = 1; i <= columnCount; i++) {
        System.out.print("+");
        for (int j = 0; j < cellWidth; j++) {
          System.out.print("-");
        }
      }
      System.out.println("+");

      for (int i = 1; i <= columnCount; i++) {
        String columName = metaData.getColumnName(i);
        columName = columName.substring(0, 1).toUpperCase() + columName.substring(1).toLowerCase();
        System.out.print("|");
        System.out.printf("%-" + cellWidth + "s", columName);
      }
      System.out.println("|");

      // Print separator
      for (int i = 1; i <= columnCount; i++) {
        System.out.print("+");
        for (int j = 0; j < cellWidth; j++) {
          System.out.print("-");
        }
      }
      System.out.println("+");

      // Print rows
      while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
          System.out.print("|");
          System.out.printf("%-" + cellWidth + "s", rs.getString(i));
        }
        System.out.println("|");

        // Print separator
        for (int i = 1; i <= columnCount; i++) {
          System.out.print("+");
          for (int j = 0; j < cellWidth; j++) {
            System.out.print("-");
          }
        }
        System.out.println("+");
      }

    } catch (Exception e) {
      System.err.println("Eror" + e.getMessage());
    }
  }

  public static void tambah() {
    try {

      String npm = InputUtil.inputString("Masukan NPM anda");
      String nama = InputUtil.inputString("Masukan Nama anda");
      String jurusan = InputUtil.inputString("Masukan Jurusan anda");
      String no_telp = InputUtil.inputString("Masukan No Telp anda");
      String email = InputUtil.inputString("Masukan Email anda");

      stm.executeUpdate(
          "insert into user (npm, nama, jurusan, no_telp, email) values('" + npm + "','" + nama + "','" + jurusan
              + "','" + no_telp + "','" + email + "');");

    } catch (Exception e) {
      System.err.println("error" + e.getMessage());
    }
  }

  public static void ubah() {
    try {

      String npm = InputUtil.inputString("Masukan NPM anda");
      String nama = InputUtil.inputString("Masukan Nama anda");
      String jurusan = InputUtil.inputString("Masukan Jurusan anda");
      String no_telp = InputUtil.inputString("Masukan No Telp anda");
      String email = InputUtil.inputString("Masukan Email anda");

      stm.executeUpdate("update user set npm='" + npm + "', nama='" + nama + "', jurusan='" + jurusan + "', no_telp='"
          + no_telp + "', email='" + email + "' where npm='" + npm + "';");

    } catch (Exception e) {
      System.err.println("eror" + e.getMessage());
    }
  }

  public static void hapus() {
    try {

      String npm = InputUtil.inputString("Masukan NPM anda");

      stm.executeUpdate("delete from user where npm='" + npm + "';");

    } catch (Exception e) {
      System.err.println("error" + e.getMessage());
    }
  }

  public static void keluar() {
    try {
      System.exit(0);
    } catch (Exception e) {
      System.err.print("error" + e.getMessage());
    }
  }

  public static void main(String[] args) {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int c;
    String k;
    System.out.println("============ Menu Utama ============");
    System.out.println("========== 1.Lihat Data ============"); // select
    System.out.println("========== 2.Tambah Data ==========="); // insert
    System.out.println("========== 3.Ubah Data ============="); // update
    System.out.println("========== 4.Hapus Data ============"); // delete
    System.out.println("========== 5.Keluar ================"); // keluar

    try {
      // register driver
      Class.forName(JDBC_DRIVER);

      conn = DriverManager.getConnection(DB_URL);
      stm = conn.createStatement();

      System.out.print("Apakah anda ingin menginput lagi:");

      while ((k = br.readLine()).equals("y")) {
        System.out.print("Masukan angka yang anda ingin pilih:");
        c = Integer.parseInt(br.readLine());

        switch (c) {
          case 1:
            lihat();
            break;
          case 2:
            tambah();
            break;
          case 3:
            ubah();
            break;
          case 4:
            hapus();
            break;
          case 5:
            keluar();
            break;

          default:
            System.out.println("Salah ketik");
        }
      }
      stm.close();
      conn.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}