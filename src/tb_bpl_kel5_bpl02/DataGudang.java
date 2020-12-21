package tb_bpl_kel5_bpl02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class DataGudang {
	    static String DB_URL = "jdbc:mysql://localhost:3306/bpltb_kel5?serverTimezone=Asia/Jakarta";
	    static String USERNAME = "root";
	    static String PASSWORD = "";
	    
	    static Connection connection;
	    static Statement stmt;
	    public DataGudang() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	        } catch (ClassNotFoundException | SQLException e) {
	            System.out.println("Terjadi Kesalahan : Driver tidak ditemukan");
	        }
	    }
	    
	    public int tambah(Gudang gudang)
	    {
	        Integer result = 0;
	        try {
	            String sql = "INSERT INTO barang (sku,nama, stok, harga_beli, harga_jual) VALUES(?,?, ?, ?, ? )";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, gudang.getsku());
	            statement.setString(2, gudang.getnama());
	            statement.setInt(3, gudang.getstok());
	            statement.setInt(4, gudang.getharga_beli());
	            statement.setInt(5, gudang.getharga_jual());
	            
	            result = statement.executeUpdate();
	            System.out.println("Berhasil input data");
	        } catch (SQLException e) {
	            System.out.println("Terjadi kesalahan input data");
	        }
	        return result;
	    }
	    
	    public ArrayList<Gudang> getAll()
	    {
	        Statement statement;
	        ArrayList<Gudang> listGudang = new ArrayList<>();
	        try {
	            statement = connection.createStatement();
	            String sql = "SELECT * FROM barang";
	            
	            ResultSet result = statement.executeQuery(sql);
	            
	            while(result.next()){
	                Gudang gudang = new Gudang(
	                    result.getString("sku"),
	                    result.getString("nama"),
	                    result.getInt("stok"),
	                    result.getInt("harga_beli"),
	                    result.getInt("harga_jual")
	                    
	                );
	                listGudang.add(gudang);
	            }
	        }catch(SQLException e){
	            System.out.println("Terjadi Kesalahan. Cek Data");
	            System.out.println(e.getMessage());
	        }
	        return listGudang;
	    }
	    
	    public int update(String sku, Gudang gudang)
	    {
	        Integer result = 0;
	        String sql = "UPDATE barang SET nama = ?, stok = ?, harga_beli = ?, harga_jual = ? WHERE sku = ?";
	        PreparedStatement statement;
	        try {
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, gudang.getnama());
	            statement.setInt(2, gudang.getstok());
	            statement.setInt(3, gudang.getharga_beli());
	            statement.setInt(4, gudang.getharga_jual());
	            statement.setString(5, sku);
	            result = statement.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println("Terjadi kesalahan query");
	        }
	        return result;
	    }
	    
	    public int hapus (String sku)
	    {
	        int result = 0;
	        String sql = "DELETE FROM barang WHERE sku = ?";
	        PreparedStatement statement;
	        try {
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, sku);
	            
	            result= statement.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    public Gudang get(String sku){
	        String sql = "SELECT * FROM barang WHERE sku = ?";
	        Gudang gudang = new Gudang();
	        
	        PreparedStatement statement;
	        try {
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, sku);

	            ResultSet rs = statement.executeQuery();
	            rs.next();
	            gudang = new Gudang(
	            		rs.getString("sku"),
	                    rs.getString("nama"),
	                    rs.getInt("stok"),
	                    rs.getInt("harga_beli"),
	                    rs.getInt("harga_jual")
	            );
	        } catch (SQLException e) {
	            System.out.println("Terjadi kesalahan query");
	        }
	        return gudang;
	    }
	    
	    public ArrayList<Gudang> cari(String keyword)
	    {
	        //Statement statement = connection.createStatement();
	        ArrayList<Gudang> listGudang = new ArrayList<>();
	        
	        PreparedStatement statement;
	        try {
	            
	            String sql = "SELECT * FROM barang WHERE nama LIKE ?"; 
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, "%" +keyword + "%");
	            
	            ResultSet result = statement.executeQuery();
	            
	            while(result.next()){
	               Gudang gudang = new Gudang(
	                		result.getString("sku"),
	                        result.getString("nama"),
	                        result.getInt("stok"),
	                        result.getInt("harga_beli"),
	                        result.getInt("harga_jual")
	                );
	                listGudang.add(gudang);
	                
	            }
	        } catch (SQLException e) {
	            System.out.println("Terjadi kesalahan query");
	        }
	        return listGudang;
	    }
	    public static void restok ()throws SQLException
	    {
	    	
			Scanner sc = new Scanner (System.in);
			System.out.println("==========================");
	        System.out.println("======RE-STOK BARANG======");
	        System.out.println("==========================");
			System.out.print  ("SKU Barang\t: ");
			String sku = sc.nextLine();
			
			try {
				stmt = connection.createStatement();
				String sql = "SELECT * FROM barang WHERE sku = '"+sku+"'";
				ResultSet result = stmt.executeQuery(sql);
				if (result.next()) {
					String id = result.getString("sku");
					Integer stok_db = result.getInt("stok");
					System.out.print("Penambahan\t : ");
					Integer tambah = sc.nextInt();
					if (tambah>0) {
					int newstock = Integer.valueOf(stok_db)+Integer.valueOf(tambah);
					String sql2 = "UPDATE barang SET stok = '"+newstock+"' WHERE sku='"+sku+"'";
					stmt.execute(sql2);
					stmt.close();
					System.out.println("stok berhasil diupdate!");}
				
				else {
					System.out.println("bilangan yang dimasukkan salah!");	
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
