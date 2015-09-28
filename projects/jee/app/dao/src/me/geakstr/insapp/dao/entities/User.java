package me.geakstr.insapp.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(nullable = false)
	private String username;

	@Column(nullable = false, unique = false)
	private String password;
	
	@Column(nullable = false, unique = false)
	private String fio;
	
	@Column(nullable = false, unique = false)
	private String role;

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	@Override
	public String toString() {
		return String.format(
				"User : fio = %s; username = %s; role = %s; password = %s",
				fio,
				username,
				role,
				password);
	}
}
