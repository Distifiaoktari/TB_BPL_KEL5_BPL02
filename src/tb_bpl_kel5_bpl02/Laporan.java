package tb_bpl_kel5_bpl02;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;

public class Laporan {
 static String DB_URL = "jdbc:mysql://localhost:3306/bpltb_kel5?serverTimezone=Asia/Jakarta";
	 static String USERNAME = "root";
	 static String PASSWORD = "";
  
	 static Connection connection;
	
	Scanner scan = new Scanner(System.in);
	
	private int id, harga_beli, harga_jual, jumlah, harga, hbeli_total, hjual_total, untung, tjumlah;
	private String sku, nama, noresi;
	private Date tanggal;

	

	
public void laporanHari() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
	        
	        String format  = "|%s\t| %s\t | %s\t | %s\t | %s\t | %s\t | %s\t | %s\t | %s\t |";
	        String format1 = "|%d\t| %s\t\t | %s\t | %s\t | %s\t | %d\t\t | %d\t\t | %d\t\t | %d\t |";
	        
	        System.out.print("\nMasukkan Tanggal yang akan di lihat (YYYY/MM/DDD) : ");
	        String tgl = scan.nextLine();
	        
	        String sql = "SELECT * FROM transaksi_detail INNER JOIN barang  ON transaksi_detail.sku = barang.sku INNER JOIN transaksi ON transaksi_detail.noresi = transaksi.noresi WHERE tanggal='"+tgl+"'";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        ResultSet result = statement.executeQuery(sql);
	        
	        if(result.next()==false) {
	        	System.out.println("Data Tidak Ada atau Format Salah");
	        	System.out.println("\n");
	        	laporanHari();
	        }
	        else {
	        	System.out.println("\n---------------------------------------------------------------------------------------------------------------");
	        	System.out.println("                                       TAMPILAN LAPORAN PENJUALAN HARIAN                                       ");
	        	System.out.println("---------------------------------------------------------------------------------------------------------------");
	        	System.out.printf(format, "ID", "No.Resi", "Tanggal", "SKU", "Nama", "Harga Beli", "Harga Jual", "Jumlah", "Total Harga");
	          	do {
			        	id = result.getInt("id");
			        	noresi = result.getString("noresi");
			        	tanggal = result.getDate("tanggal");
			        	sku = result.getString("sku");
			        	nama = result.getString("nama");
			        	harga_beli = result.getInt("harga_beli");
			        	harga_jual = result.getInt("harga_jual");
			        	jumlah = result.getInt("jumlah");
			        	harga = result.getInt("harga");
			        	
			        	hbeli_total=hbeli_total+harga_beli;
			        	hjual_total=hjual_total+harga_jual;
			        	
			        	System.out.println("\n");
			        	System.out.printf(format1, id, noresi, tanggal, sku, nama, harga_beli, harga_jual, jumlah, harga*jumlah);
	          	} while(result.next());
		        untung = hjual_total-hbeli_total;
		              
		        System.out.println("\n---------------------------------------------------------------------------------------------------------------");
		        System.out.println("                                                                                                                ");
		        System.out.println("                                                                                                                ");
		        System.out.println("Total penjualan barang per hari      : "+hjual_total*jumlah+"                                                   ");
		        System.out.println("Total modal barang terpakai per hari : "+hbeli_total*jumlah+"                                                   ");
		        System.out.println("Keuntungan per hari                  : "+untung*jumlah+"                                                        ");
		        System.out.println("----------------------------------------------------------------------------------------------------------------");
		        ulang();
	        } 
	        
		}
		catch (Exception e){
			 e.printStackTrace();
		}

	}
public void laporanBulan() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	        
	        
	        String format  = "| %s\t | %s\t | %s\t | %s\t | %s\t\t | %s\t | %s\t | %s\t | %s\t |";
	        String format1 = "| %d\t | %s\t\t | %s\t | %s\t | %s\t\t | %d\t\t | %d\t\t | %d\t\t | %d\t |";
	        
	    
	        System.out.print("Pilih No Bulan : ");
	        String tgl = scan.nextLine();
	        System.out.print("Masukkan Tahun (YYYY) : ");
	        String tgl1 = scan.nextLine();
	        
	        String sql = "SELECT * FROM transaksi_detail INNER JOIN barang  ON transaksi_detail.sku = barang.sku INNER JOIN transaksi ON transaksi_detail.noresi = transaksi.noresi WHERE MONTH(tanggal)='"+tgl+"' AND YEAR(tanggal)='"+tgl1+"'";
	      
	        PreparedStatement statement = connection.prepareStatement(sql);
	        ResultSet result = statement.executeQuery(sql);
	       
	        
	        if(result.next()==false) {
	        	System.out.println("\nData Tidak Ada atau Format Salah");
	        	System.out.println("\n");
	        	kembali();
	        }
	        else {
	        	System.out.println("\n---------------------------------------------------------------------------------------------------------------");
	        	System.out.println("                                       TAMPILAN LAPORAN PENJUALAN BULANAN                                      ");
	        	System.out.println("---------------------------------------------------------------------------------------------------------------");
	        	System.out.printf(format, "ID", "No.Resi", "Tanggal", "SKU", "Nama", "Harga Beli", "Harga Jual", "Jumlah", "Total");
	          	do {
			        	id = result.getInt("id");
			        	noresi = result.getString("noresi");
			        	tanggal = result.getDate("tanggal");
			        	sku = result.getString("sku");
			        	nama = result.getString("nama");
			        	harga_beli = result.getInt("harga_beli");
			        	harga_jual = result.getInt("harga_jual");
			        	jumlah = result.getInt("jumlah");
			        	harga = result.getInt("harga");
			        	
			        	hbeli_total=hbeli_total+harga_beli;
			        	hjual_total=hjual_total+harga_jual;
			        	tjumlah=tjumlah+jumlah;
			        	System.out.println("\n");
			        	System.out.printf(format1, id, noresi, tanggal, sku, nama, harga_beli, harga_jual, jumlah, harga*jumlah);
	          	} while(result.next());
		        untung = hjual_total-hbeli_total;
	          	System.out.println("\n---------------------------------------------------------------------------------------------------------------");
	          	System.out.println("                                                                                                               ");
		        System.out.println("                                                                                                               ");
		        System.out.println("Total penjualan barang per bulan      : "+hjual_total * tjumlah+"                                              ");
		        System.out.println("Total modal barang terpakai per bulan : "+hbeli_total * tjumlah+"                                              ");
		        System.out.println("Keuntungan per bulan                  : "+untung * tjumlah+"                                                   ");
		        System.out.println("---------------------------------------------------------------------------------------------------------------");
		        ulang();
	        }    
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
	}

	
private void kembali() {
		System.out.println("Apakah anda ingin melanjutkan program?(Y/T)");
		String masuk = scan.nextLine();
		masuk.toUpperCase();
		if(masuk.equalsIgnoreCase("Y")) {
			System.out.println("\n");
			laporanBulan();
		}
		else if(masuk.equalsIgnoreCase("T")) {
			System.out.println("\nKembali ke menu");

		}
		else {
			System.out.println("\nPilihan Salah");
			System.out.println("\n");
			kembali();
		}
	}	
	
	
private void ulang() {
		System.out.println("\n\nApakah anda ingin melanjutkan program?(Y/T)");
		String masuk = scan.nextLine();
		masuk.toUpperCase();
		if(masuk.equalsIgnoreCase("Y")) {
			System.out.println("\n");
		}
		else if(masuk.equalsIgnoreCase("T")) {
			System.out.println("Program Selesai");
			System.exit(0);
		}
		else {
			System.out.println("\nPilihan Salah");
			System.out.println("\n");
			ulang();
		}
	}
	
}
