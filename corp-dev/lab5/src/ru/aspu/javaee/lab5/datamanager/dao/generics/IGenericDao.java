package ru.aspu.javaee.lab5.datamanager.dao.generics;


public interface IGenericDao<T> {
	public abstract T find(int id);
	public abstract void insert(T t);
	public abstract void update(T t);
	public abstract void delete(T t);
}
