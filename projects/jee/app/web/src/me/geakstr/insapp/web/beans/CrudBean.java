package me.geakstr.insapp.web.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import me.geakstr.insapp.business.facades.ICrudFacade;
import me.geakstr.insapp.dao.entities.IEntity;

@ManagedBean
@ViewScoped
public abstract class CrudBean<T extends IEntity, V extends ICrudFacade<T>> extends BaseBean {	
	protected List<T> items;
	protected T item;
	protected boolean edit;

	private Class<T> clazz;
	
	protected abstract V getFacade();

	protected void init(final Class<T> clazz) {
		this.clazz = clazz;
		
		items = getFacade().findAll();
		try {
			item = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}	
	
	public void add() {
		items.add(item);
		getFacade().add(item);
		try {
			item = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void edit(final T item) {
		this.item = item;
		edit = true;
	}

	public void save() {
		getFacade().edit(item);
		edit = false;
		try {
			item = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		reload();
	}
	
	public void reload() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(final T item) {
		getFacade().delete(item);
		items.remove(item);
		
		reload();
	}

	public List<T> getItems() {
		return items;
	}

	public T getItem() {
		return item;
	}

	public boolean isEdit() {
		return edit;
	}
}
