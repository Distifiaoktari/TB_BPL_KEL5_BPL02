package tb_bpl_kel5_bpl02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class transaksipenjualan {
	Gudang gudang;
	DataGudang datagudang;
	int i=1;
	static Connection connection;
	Scanner input = new Scanner(System.in);
	
	
	public transaksipenjualan() {
		try {
			datagudang = new DataGudang();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	public void getAll() {
    	try {
			Statement statement;
			String sql = "SELECT * FROM barang";
			 statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("Pilihan Data Barang\n");
			System.out.println("No\t| SKU\t| Nama Barang \t\t| Harga\t| Stock");
			while(rs.next()) {
				 System.out.println(+i +"\t| "+rs.getString("sku")+"\t| "  +rs.getString("nama")+"\t| Rp"+rs.getInt("harga_jual")+"\t| "+rs.getInt("stok"));
				 i++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    	
	}
	public void skuinput() {
		// TODO Auto-generated method stub
		System.out.print("\nInput Nomor SKU Barang : ");
		String sku =  input.nextLine();	
		String sql = "SELECT * FROM barang WHERE sku = ?";
		try {
	          PreparedStatement statement = connection.prepareStatement(sql);
	          statement.setString(1, sku);
	          ResultSet rs = statement.executeQuery();
	          if(rs.next()) {
	        	  System.out.println("barang yang anda pesan adalah "+rs.getString("nama")+" dengan stock sebesar "+rs.getInt("stok"));
	        	  Jumlahbarang();
	          }
	          else {
	        	  System.out.println("Input Nomor SKU dengan tepat");
	        	 
	          }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ada kendala di bagian input process");
		}
	}
	public void Jumlahbarang() {		
		System.out.print("Input Jumlah Barang : ");
		int jumlah =  input.nextInt();
		jmlprocess();
	}
	
	public void jmlprocess() {
		String sql = "SELECT * FROM barang WHERE sku = ?";
		try {
	          PreparedStatement statement = connection.prepareStatement(sql);
	          String sku = null;
			statement.setString(1, sku);
	          ResultSet rs = statement.executeQuery();
	          
	          if(rs.next()) {
	        	  int dbstock=rs.getInt("stock");
	        	  int jumlah=0;
				int dbhargajual=rs.getInt("harga_jual")*jumlah;
	        	  if(jumlah<=dbstock) {
	        		  System.out.println("total harga barang : Rp"+dbhargajual);
	        		  System.out.println("pesanan anda sedang diproses");
	        	  }
	        	  else {
	        		  System.out.println("stok yang diinput melebihi kapasitas.");
		        	  Jumlahbarang();  
	        	  }
	        	  int harga_jual=dbhargajual;
	          }
	          else {
	        	  //memasukkan kembali nilainya atau keluar
	        	  System.out.println("jumlah yang dimasukkan melebihi stock yang tersedia");
	          }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Ada Kendala di bagian jmlprocess");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public void hasilorder() {
		String sql = "SELECT * FROM transaksi by tanggal DESC";
		try {
			Statement statement;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
	          if(rs.next()) {
	        	  String dbnoresi=rs.getString("noresi");
	        	  System.out.println("Nomor resi Pembelian : "+dbnoresi);
	        	  String noresi=dbnoresi;        	  
	          }
	          else {
	        	  System.out.println("masukkan Nomor SKU dengan tepat");
	        	 
	          }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ada kendala di bagian hasilorder");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void input() {
	try {
		Statement stmt = connection.createStatement();		
		String Query = "INSERT INTO transaksi_detail(jumlah,harga,noresi,sku) "
				+ "VALUES (?,?, ?, ?, ? )";
		stmt.executeUpdate(Query);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void show() {
		i=1;
		try {
			String sql = "SELECT * FROM transaksi_detail JOIN  barang ON transaksi_detail.sku=barang.sku "
					+ "JOIN transaksi on transaksi_detail.noresi=transaksi.noresi "
					+ "ORDER BY tanggal DESC ";
			Statement statement = null;
			ResultSet rs = statement.executeQuery(sql);
			System.out.println(" Data Pesanan Barang\n");
			System.out.println("No\t| SKU\t| Nama Barang \t\t| Harga\t| stock");
			while(rs.next()) {
				 System.out.println(+i +"\t| "+rs.getString("sku")+"\t| "  +rs.getString("nama")+"\t| Rp"+rs.getInt("harga")+"\t| "+rs.getInt("stok"));
				 i++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
}
}
