package ru.aspu.javaee.lab5.datamanager.dao;

import java.util.List;

import ru.aspu.javaee.lab5.datamanager.dao.generics.IGenericDao;
import ru.aspu.javaee.lab5.entities.Book;
import ru.aspu.javaee.lab5.entities.Cycle;

public interface CycleDao extends IGenericDao<Cycle> {
	public abstract List<Cycle> getAll();
	public abstract List<Book> getCycle(Cycle cycle);
}
