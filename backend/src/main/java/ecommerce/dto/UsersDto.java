package ecommerce.dto;

public class UsersDto {
	
	private int userID;
	private String email;
	private String password;
	private String name;
	private String surname;
	private String birthdate;
	private String phone;
	private String token;
	
	public UsersDto () {}
	
	public UsersDto (int userID, String email, String password, String name, String surname, String birthdate, String phone, String token) {
		
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.phone = phone;
		this.token = token;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

}
