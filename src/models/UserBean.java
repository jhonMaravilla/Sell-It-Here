package models;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import util.HashPassword;

public class UserBean implements Serializable {
	private int id;
	private String fname;
	private String lname;
	private String username;
	private String salt;
	private String password;
	private String email;
	private String street;
	private String city;
	private String province;
	private String postal;
	private String phone;
	private String creditcard;
	private java.sql.Timestamp dateCreated;
	
	
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserBean(String fname, String lname, String username,String salt, String password, String email, String street,
			String city, String province, String postal, String phone, Timestamp dateCreated) {
		super();
//		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.salt = salt;
		this.password = password;
		this.email = email;
		this.street = street;
		this.city = city;
		this.province = province;
		this.postal = postal;
		this.phone = phone;
		this.dateCreated = dateCreated;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public java.sql.Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.sql.Timestamp dateCreate) {
		this.dateCreated = dateCreate;
	}

	@Override
	public String toString() {
		return "UserBean [fname=" + fname + ", lname=" + lname + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", street=" + street + ", city=" + city + ", province="
				+ province + ", postal=" + postal + ", phone=" + phone + ", dateCreated=" + dateCreated + "]";
	}
	
	public boolean authenticate(String password) {
		String hash = HashPassword.getHashPassword(password, this.salt);
		return hash.equals(this.password);
	}
	
	public void hashPassword(String raw) {
		this.salt = HashPassword.getSalt();
		this.password = HashPassword.getHashPassword(raw, this.salt);
	}
	
}
