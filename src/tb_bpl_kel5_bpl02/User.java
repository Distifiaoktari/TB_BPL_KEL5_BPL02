package tb_bpl_kel5_bpl02;

public class User {

	String username;
	String password;
	String email;
	
	public User() {

	}
	
	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public User(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }
    
    public User(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }
	
}
