package me.geakstr.insapp.web;

public class SessionInfo {
	public boolean isAdmin = false;
	public boolean isEmployee = false;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}	
}
