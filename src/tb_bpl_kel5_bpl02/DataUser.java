package tb_bpl_kel5_bpl02;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class DataUser {

	Scanner scanner = new Scanner(System.in);
	User user;
	KelolaUser kelolaUser;
    static Integer cek = 2;
    private String email1 = null;
	private String email2 = null;
	private String email3 = null;
	
	public DataUser() {
		try {
			kelolaUser = new KelolaUser();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}

	
	void login() throws SQLException {
		// TODO Auto-generated method stub 
		System.out.print("Email    : ");
		String email = scanner.nextLine();
		
		System.out.print("Password : ");
		String password = scanner.nextLine();
	        
		User user = new User(email, password);
	    
		if(cek == 2) 
		{
			email1 = email;
		} 
		else if(cek == 1) 
		{
			email2 = email;
			if(!email2.equals(email1)) 
			{
				email1 = email;
				cek = 2;
				email2 = null;
			}
		} 
		else if(cek == 0) 
		{
			email3 = email;
			if(!email3.equals(email2)) 
			{
				cek = 2;
				email1 = email;
				email2 = null;
				email3 = null;
			}
		}
		
		if(kelolaUser.login(user) == 1){
			 System.out.println("User berhasil Login!");
			 
		}
		else {
			while(cek>0) {
				cek--;
				login();
			}
		}

	}
	
	void cariUser() throws SQLException {
		System.out.println(" ");
        System.out.print("Masukkan username yang akan dicari : ");
        String keyword = scanner.nextLine();
        
        ArrayList<User> listUser = kelolaUser.cari(keyword);
        
        for(User user : listUser){
            System.out.print(user.getUsername());
            System.out.print("\t");
            System.out.print(user.getEmail());
            System.out.print("\t");
            System.out.println(user.getPassword());
        }
    }
	
	 void editUser() {
        lihatUser();
        System.out.println(" ");
        System.out.print("username akun yang akan diedit ? ");
        String username = scanner.nextLine();
            
        User user = kelolaUser.get(username);
            
        System.out.print("Email ["+user.getEmail()+"] : ");
        String email = scanner.nextLine();
        
            
        System.out.print("Password ["+user.getPassword()+"] : ");
        String password = scanner.nextLine();
            
        User userUpdate = new User(email, password);
            
        if(kelolaUser.updateUser(username, userUpdate) > 0){
        	System.out.println("Update Akun Berhasil Dilakukan!");
        }
    }
	
	
	void hapusUser(){        
        lihatUser();
        System.out.println(" ");
        System.out.print("Username akun yang akan dihapus ? ");
        String username = (scanner.nextLine());
        
        if(kelolaUser.hapusUser(username) > 0 ){
            System.out.println("User berhasil di hapus!");
        }   
    }
	
	 void SignUp() {	        
		 System.out.print("Username : ");
		 String username = scanner.nextLine();
		 System.out.print("Email    : ");
		 String email = scanner.nextLine();
		 System.out.print("Password : ");
		 String password = scanner.nextLine();
	        
		 User user = new User(username, email, password);
	        
		 if(kelolaUser.tambahUser(user) > 0){
			 System.out.println("Akun Anda Berhasil Ditambahkan!");
		 }       
	 }
	
	 void lihatUser(){
		 TreeMap<String, User> listUser = kelolaUser.getAll();
	        
		 for(Map.Entry list : listUser.entrySet()){  
			 User userList = (User) list.getValue();
	         System.out.print(list.getKey());
	         System.out.print("\t| ");
	         System.out.print(userList.getEmail());
	         System.out.print("\t| ");
	         System.out.print(userList.getPassword());
	         System.out.print("\t| ");
	         System.out.println(" ");
	     } 
	 }
	
}
