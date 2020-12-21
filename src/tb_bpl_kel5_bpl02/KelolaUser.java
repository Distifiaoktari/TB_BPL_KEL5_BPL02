package tb_bpl_kel5_bpl02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;

public class KelolaUser {

	static String DB_URL = "jdbc:mysql://localhost:3306/bpltb_kel5?serverTimezone=Asia/Jakarta";
    static String USERNAME = "root";
    static String PASSWORD = "";
    
    Random random = new Random();
    program Program;
    static Connection connection;
    
    public KelolaUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Terjadi Kesalahan : Driver tidak ditemukan");
        }
    }
	
    
    public String resetPassword() {
		char[] karakter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder builder = new StringBuilder();
		String hasil = null;
		
		for(Integer a=0; a<10; a++) {
			Character acak = karakter[random.nextInt(karakter.length)];
			builder.append(acak);
		}
		
		hasil = builder.toString();
		builder.delete(0, 15);
		
		return hasil;
	}
    
    
    public int login(User user) {
    	Integer result = 0;
    	String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
    	PreparedStatement statement;
    	try {
    		 statement = connection.prepareStatement(sql);
             statement.setString(1, user.getEmail());
             statement.setString(2, user.getPassword());
             ResultSet rs = statement.executeQuery();
             
             if(rs.next()) {
            	 String query = "UPDATE user SET login_terakhir = now() WHERE email = ?";
            	 statement = connection.prepareStatement(query);
            	 statement.setString(1,  user.getEmail());
            	 result= statement.executeUpdate();	 
             }
             
             else {
            	 if(program.cek > 0) {
            		 System.out.println(" ");
            		 System.out.println("Username Atau Password Yang Diinput Salah");
            		 System.out.println("Silahkan Input Kembali");
            		 System.out.println(" ");
            	 }
            	 else if(program.cek <= 0) {
            		 System.out.println(" ");
            		 System.out.println("Password Anda Telah Direset");
            		 String reset = "UPDATE user SET password=? WHERE email =?";
            		 statement = connection.prepareStatement(reset);
            		 statement.setString(1, resetPassword());
            		 statement.setString(2, user.email);
            		 statement.executeUpdate();
            	}
             }
    	}
    	catch(SQLException e){
            System.out.println("Terjadi Kesalahan");
            System.out.println(e.getMessage());
        }
    	return result;
    }
    
    
    public void Logout() throws Exception{
    	System.out.println("Anda Telah Logout");
    	program.main(null);
    }
    
    
    public int tambahUser(User user)
    {
    	Integer result = 0;
    	PreparedStatement statement;
    	
    	if (user.email.contains("@")) {
	    	try {
	        	String periksa = "SELECT username FROM user WHERE username = ?";
				statement = connection.prepareStatement(periksa);
				statement.setString(1, user.getUsername());
				ResultSet hasil = statement.executeQuery();
						
				if (hasil.next()) {
					System.out.println("Maaf Username Telah Terdaftar");
				}
	        	
				else {
		            String sql = "INSERT INTO user (username, login_terakhir, email, password) VALUES(?, now(), ?, ?)";
		            statement = connection.prepareStatement(sql);
		            statement.setString(1, user.getUsername());
		            statement.setString(2, user.getEmail());
		            statement.setString(3, user.getPassword());
		            
		            result = statement.executeUpdate();
				}
	        } 
	        catch (SQLException e) {
	            System.out.println("Terjadi Kesalahan Dalam Pembuatan Akun");
	        }
    	}
    	else {
    		System.out.println("Inputkan Email Dengan Alamat Yang Benar!");
    	}
        return result;
    }
    
    
    public TreeMap<String, User> getAll()
    {
        Statement statement;
        TreeMap<String, User> listUser = new TreeMap<>();
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM user";
            
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                User user= new User(
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("password")
                );
                listUser.put(result.getString("username"), user);
            }
        }
        catch(SQLException e){
            System.out.println("Terjadi Kesalahan. Silahkan Cek Kembali Data User");
            System.out.println(e.getMessage());
        }
        return listUser;
    }
    
    
    public int updateUser(String username, User user)
    {
        Integer result = 0;
        String sql = "UPDATE user SET email = ?, password = ? WHERE username = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, username);
            result = statement.executeUpdate();
        } 
        catch (SQLException e) {
            System.out.println("Terjadi Kesalahan Query");
        }
        return result;
    }
    
    
    public int hapusUser (String username)
    {
        int result = 0;
        String sql = "DELETE FROM user WHERE username = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            
            result= statement.executeUpdate();
            
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public User get(String username){
        String sql = "SELECT * FROM user WHERE username = ?";
        User user = new User();
        
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            rs.next();
            user = new User(
            		rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
            );
        } catch (SQLException e) {
            System.out.println("Terjadi Kesalahan Query");
        }
        return user;
    }
    
    
    public ArrayList<User> cari(String keyword)
    {
        //Statement statement = connection.createStatement();
        ArrayList<User> listUser = new ArrayList<>();
        
        PreparedStatement statement;
        try {
            
            String sql = "SELECT * FROM user WHERE username LIKE ?"; 
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" +keyword + "%");
            
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
               User user = new User(
                		result.getString("username"),
                        result.getString("email"),
                        result.getString("password")
                );
                listUser.add(user);
                
            }
        } catch (SQLException e) {
            System.out.println("Terjadi Kesalahan Query");
        }
        return listUser;
    }
	
}
