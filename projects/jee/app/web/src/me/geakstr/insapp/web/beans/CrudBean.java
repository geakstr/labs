package me.geakstr.insapp.web.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.geakstr.insapp.business.facades.ICrudFacade;
import me.geakstr.insapp.dao.entities.IEntity;

@ManagedBean
@ViewScoped
public abstract class CrudBean<T extends IEntity, V extends ICrudFacade<T>> extends BaseBean {
	@EJB
	protected ICrudFacade<T> facade;

	protected List<T> items;
	protected T item;
	protected boolean edit;

	private Class<T> clazz;

	protected void init(Class<T> clazz) {
		this.clazz = clazz;
		items = facade.getAll();
		try {
			item = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void add() {
		items.add(item);
		facade.add(item);
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
		facade.edit(item);
		edit = false;
		try {
			item = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void delete(final T item) {
		facade.delete(item);
		items.remove(item);
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
