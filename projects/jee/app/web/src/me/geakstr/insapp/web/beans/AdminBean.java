package me.geakstr.insapp.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.business.facades.AdminFacade;
import me.geakstr.insapp.dao.entities.User;

@ManagedBean
@ViewScoped
public class AdminBean extends BaseBean {
	@EJB
	private AdminFacade adminFacade;
	
	private List<User> users;
	private User user = new User();
	private boolean edit;
	
	@PostConstruct
	public void init() {
		users = adminFacade.getAllUsers();
	}
	
	public void add() {
        users.add(user);
        adminFacade.addUser(user);
        user = new User();
    }

	
	public void edit(final User user) {
		this.user = user;
		edit = true;
	}
	
	public void save() {
		adminFacade.editUser(user);
        user = new User();
        edit = false;
    }
	
	public void delete(final User user) {
		adminFacade.deleteUser(user);
        users.remove(user);
    }

	public List<User> getUsers() {
		return users;
	}

	public User getUser() {
        return user;
    }

    public boolean isEdit() {
        return edit;
    }
}
