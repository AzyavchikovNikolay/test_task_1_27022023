package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String login="";
	private String password="";
	private String role="";
	private String surname="";
	private String name="";
	private String phone="";
	private String email="";
	private String birthday="";
	
	public User() {
		super();
	}
	
	public User(String login, String password, String role, String surname, String name, String phone, String email, String birthday) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
		this.surname = surname;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login=login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role=role;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname=surname;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}

	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday=birthday;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(login, password, role, surname, name, phone, email, birthday);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		User user = (User) obj;
		return Objects.equals(login, user.login)&&Objects.equals(password, user.password)&&
				Objects.equals(role, user.role)&&Objects.equals(surname, user.surname)&&
				Objects.equals(name, user.name)&&Objects.equals(phone, user.phone)&&
				Objects.equals(email, user.email)&&Objects.equals(birthday, user.birthday);
	}
}
