package tb_bpl_kel5_bpl02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Date;


public class transaksipenjualan extends datatransaksi{
	public String skubarang;
	public String noresi;
	public int hargabarang;
	public int id=1;
	public int jml;
	public int hargajual;
	String user,pilihan;
	int dbstok;
	public String Date;
	
	Connection conn;
	Statement statement;
	PreparedStatement stmt;

	Scanner input = new Scanner(System.in);
	Date date = new Date();
	
	public void insertall() {
		getAll();
		skuinput();
		inputprocess();
		hasilorder();
		input();
		show();
	}
	
	public transaksipenjualan() {	
		try {
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/bpltb_kel5?serverTimezone=Asia/Jakarta", "root", "");
			
		} catch (SQLException e) {
			System.out.println("Aktifkan SQL terlebih dahulu ");
			e.printStackTrace();
		}
	}
	
	public void noresi() {
		System.out.print("\nMasukkan No Resi : ");
		noresi =  input.nextLine();	

		try {
			Statement stmt = conn.createStatement();
			String Query = "INSERT INTO transaksi(noresi,tanggal,username) "
					+ "VALUES ('"+noresi+"','"+date+"','"+ user +"')";
			stmt.executeUpdate(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getAll() {
    	try {
			statement = conn.createStatement();
			String sql = "SELECT * FROM barang";
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("Pilihan Data Barang\n");
			System.out.println("ID\t| SKU\t\t| Nama Barang \t\t| Harga\t\t| stok");
			id=1;
			while(rs.next()) {
				 System.out.println(+id +"\t| "+rs.getString("sku")+"\t\t| "  +rs.getString("nama")+"\t\t| Rp"+rs.getInt("harga_jual")+"\t| "+rs.getInt("stok"));
				 id++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    	
	}
	public void skuinput() {
		// TODO Auto-generated method stub
		System.out.print("\nMasukkan Nomor SKU Barang : ");
		skubarang =  input.next();			
	}
	public void inputprocess() {
		String sql = "SELECT * FROM barang WHERE sku = ?";
		try {
	          PreparedStatement statement = conn.prepareStatement(sql);
	          statement.setString(1, skubarang);
	          ResultSet rs = statement.executeQuery();
	          if(rs.next()) {
	        	  System.out.println("barang pesanan anda adalah "+rs.getString("nama")+" dengan stok sebanyak "+rs.getInt("stok"));
	        	  Jumlahbarang();
	          }
	          else {
	        	  System.out.println("Masukkan Nomor SKU dengan tepat");
	        	  this.skubarang=null;
	        	  skuinput();
	          }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ada masalah di proses inputan");
		}
	}
	
	public void Jumlahbarang() {		
		System.out.print("Masukkan Jumlah Barang : ");
		jml =  input.nextInt();
		jmlprocess();
	}
	
	public void jmlprocess() {
		String sql = "SELECT * FROM barang WHERE sku = ?";
		try {
	          PreparedStatement statement = conn.prepareStatement(sql);
	          statement.setString(1, skubarang);
	          ResultSet rs = statement.executeQuery();
	          
	          if(rs.next()) {
	        	  dbstok=rs.getInt("stok");
	        	  int dbhargajual=rs.getInt("harga_jual")*jml;
	        	  if(jml<=dbstok) {
	        		  System.out.println("total pesanan : Rp"+dbhargajual);
	        		  System.out.println("pesanan anda sedang diproses");
	        	  }
	        	  else {
	        		  System.out.println("stok yang dimasukkan melebihi kapasitas.");
		        	  Jumlahbarang();  
	        	  }
	        	  hargajual=dbhargajual;
	          }
	          else {
	        	  //memasukkan kembali nilainya atau keluar
	        	  System.out.println("jumlah yang dimasukkan melebihi stok yang tersedia");
	          }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Ada masalah di bagian pemrosesan jumlah");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void hasilorder() {
		String sql = "SELECT * FROM transaksi ORDER by tanggal DESC";
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
	          if(rs.next()) {
	        	  System.out.println("Nomor resi Pembelian : "+noresi);       	  
	          }
	          else {
	        	  System.out.println("masukkan Nomor SKU dengan tepat");
	        	  this.skubarang=null;
	        	 
	          }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ada masalah di bagian pemrosesan order");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void input() {
	try {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM transaksi_detail";
		ResultSet rs = statement.executeQuery(sql);
		id=1;
		while(rs.next()) {
			 id++;
		}
		dbstok=dbstok-jml;
		String Query = "UPDATE barang SET stok='"+dbstok+"' WHERE sku='"+skubarang+"'";
		stmt.executeUpdate(Query);
		
		Query = "INSERT INTO transaksi_detail(id,sku,jumlah,harga,noresi) "
				+ "VALUES ('"+id+"','"+ skubarang +"', '"+ jml +"', '"+ hargajual +"' , '"+ noresi +"')";
		stmt.executeUpdate(Query);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void show() {
		id=1;
		try {
			String sql = "SELECT * FROM transaksi_detail JOIN  barang ON transaksi_detail.sku=barang.sku "
					+ "JOIN transaksi on transaksi_detail.noresi=transaksi.noresi "
					+ "ORDER BY tanggal DESC ";
			ResultSet rs = statement.executeQuery(sql);
			System.out.println(" Data Pesanan Barang\n");
			System.out.println("ID\t| SKU\t| Nama Barang \t\t| Harga\t| stok");
			while(rs.next()) {
				 System.out.println(+id +"\t| "+rs.getString("sku")+"\t| "  +rs.getString("nama")+"\t\t\t| Rp"+rs.getInt("harga")+"\t| "+rs.getInt("stok"));
				 id++;
			}
		} catch (SQLException e) {
			System.out.println("Ada Error");
		}
		
	}
	

}
