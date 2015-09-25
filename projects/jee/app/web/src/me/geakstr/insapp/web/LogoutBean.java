package me.geakstr.insapp.web;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletException;

@ManagedBean
@RequestScoped
public class LogoutBean extends BaseBean {	
	public void logout() throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", new java.util.Date().toString());
	    if (request.getSession(false) != null) {
	        request.getSession(false).invalidate();
	    }
	    request.logout();
	    response.sendRedirect("/web/views/index.xhtml");
	}
}