package ru.aspu.javaee.lab4.datamanager.dao.generics;

import ru.aspu.javaee.lab4.datamanager.DataManager;

public class GenericDaoJpa<T> implements IGenericDao<T> {
	private Class<T> type;

	public GenericDaoJpa(Class<T> type) {
		this.type = type;
	}

	@Override
	public T find(int id) {
		return DataManager.getInstance().find(type, id);
	}

	@Override
	public void insert(T t) {
		DataManager.getInstance().getTransaction().begin();
		DataManager.getInstance().persist(t);
		DataManager.getInstance().getTransaction().commit();
	}

	@Override
	public void update(T t) {
		DataManager.getInstance().getTransaction().begin();
		DataManager.getInstance().merge(t);
		DataManager.getInstance().getTransaction().commit();
	}

	@Override
	public void delete(T t) {
		DataManager.getInstance().getTransaction().begin();
		DataManager.getInstance().remove(t);
		DataManager.getInstance().getTransaction().commit();
	}
}
