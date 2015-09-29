package me.geakstr.insapp.web.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.geakstr.insapp.web.SessionInfo;

@ManagedBean
@ViewScoped
public class BaseBean {
	private ExternalContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected SessionInfo sessionInfo;
		
	@PostConstruct
	public void setter() {
		context = FacesContext.getCurrentInstance().getExternalContext();
		
		request = (HttpServletRequest) context.getRequest();
		response = (HttpServletResponse) context.getResponse();
		sessionInfo = new SessionInfo();
				
		if (context != null) {
			sessionInfo.isAdmin = context.isUserInRole("ADMIN");
			sessionInfo.isEmployee = context.isUserInRole("EMPLOYEE");
			
			if (request.getUserPrincipal() != null) {
				sessionInfo.name = request.getUserPrincipal().getName();
			}
		}
	}

	public SessionInfo getSessionInfo() {
		return sessionInfo;
	}

	public void setSessionInfo(SessionInfo sessionInfo) {
		this.sessionInfo = sessionInfo;
	}	
}
