import java.sql.*;    // koneksi database
import java.io.*;     // input output
import javax.swing.*;  // mengkombinasi script lebih dari 1 dijadikan 1 form 

public class Koneksi
{

String str;
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    public void lihat()
    {
        try{
            //load database
            Class.forName("org.sqlite.JDBC");

            //buat koneksi
            Connection koneksi=DriverManager.getConnection("JDBC:sqlite:warehouse.db");
            //buat statement
            Statement stm=koneksi.createStatement();
            ResultSet rs=stm.executeQuery("select * from barang"); 
            
            
            System.out.println(rs.getMetaData());
            

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
        }catch(Exception e){System.err.println("Eror"+e.getMessage());}
    
   }
 
    public void tambah()
    {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection koneksi=DriverManager.getConnection("JDBC:sqlite:belajar.db");

            Statement stm=koneksi.createStatement();

            System.out.print("Masukan Nama anda:");
            String a=br.readLine();
            System.out.print("Masukan Alamat anda:");
            String b=br.readLine();
            System.out.print("Masukan Npm anda:");
            int c=Integer.parseInt(br.readLine());
            
            stm.executeUpdate("insert into air (Nama,Alamat,Npm) values('"+a+"','"+b+"','"+c+"');");
        
            stm.close();
            koneksi.close();
        
        }catch(Exception e){System.err.println("error"+e.getMessage());}
    }

 public void ubah()
 {
     try{
         Class.forName("org.sqlite.JDBC");
         Connection koneksi=DriverManager.getConnection("JDBC:sqlite:belajar.db");
         Statement stm=koneksi.createStatement();

         System.out.print("Masukan Nama baru:");
         String a=br.readLine();
         System.out.print("Masukan Alamat baru:");
         String b=br.readLine();
         System.out.print("Masukan Npm lama:");
         int c=Integer.parseInt(br.readLine());
         stm.executeUpdate("update air set Nama='"+a+"',Alamat='"+b+"',Npm='"+c+"' where Npm='"+c+"';");
       
         stm.close();
         koneksi.close();
        }catch(Exception e){System.err.println("eror"+e.getMessage());}
    }

 public void hapus()
 {
     try{
         Class.forName("org.sqlite.JDBC");
         Connection koneksi=DriverManager.getConnection("JDBC:sqlite:belajar.db");
         Statement stm=koneksi.createStatement();
         
         System.out.print("Masukan Nama yang ingin anda hapus:");
         String a=br.readLine();
         System.out.print("Masukan Npm yang ingin anda hapus:");
         int c=Integer.parseInt(br.readLine());
         
         stm.executeUpdate("delete from air where Npm='"+c+"';");
         
         stm.close();
         koneksi.close();
        }catch(Exception e){System.err.println("error"+e.getMessage());}
    }      

  public void keluar()
  { 
      try{
      System.exit(0);
  }catch(Exception e){System.err.print("error"+e.getMessage());}
 }

 public static void main(String[]args)
 {
     Koneksi kita=new Koneksi();
 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 int c;
 String k;
 System.out.println("============Menu Utama============");
 System.out.println("==========1.Lihat Data============");    // select
 System.out.println("==========2.Tambah Data===========");    // insert
 System.out.println("==========3.Ubah Data=============");    // update
 System.out.println("==========4.Hapus Data============");    // delete
 System.out.println("==========5.Keluar================");    // keluar
 
 try{
 System.out.print("Apakah anda ingin menginput lagi:");
 while((k=br.readLine()).equals("y"))
 {
 System.out.print("Masukan angka yang anda ingin pilih:");
 
 
 c=Integer.parseInt(br.readLine());
 
 switch(c)
 {
     case 1:kita.lihat(); break;
     case 2:kita.tambah(); break;
     case 3:kita.ubah(); break;
     case 4:kita.hapus(); break;
     case 5:kita.keluar(); break;
     
     default:System.out.println("Salah ketik");
 }
 

}     
}catch(Exception e){System.err.println("Error"+e.getMessage());

}
}
}
