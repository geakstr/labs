package me.geakstr.insapp.business.facades;

import java.util.List;

public interface ICrudFacade<T> {
	List<T> findAll();
	void add(T item);
	void edit(T item);
	void delete(T item);
}
