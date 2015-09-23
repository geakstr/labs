package ru.aspu.javaee.lab5.datamanager.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import ru.aspu.javaee.lab5.datamanager.DataManager;
import ru.aspu.javaee.lab5.datamanager.dao.CycleDao;
import ru.aspu.javaee.lab5.datamanager.dao.generics.GenericDaoJpa;
import ru.aspu.javaee.lab5.entities.Book;
import ru.aspu.javaee.lab5.entities.Cycle;

public class CycleDaoJpa extends GenericDaoJpa<Cycle> implements CycleDao {
	public CycleDaoJpa() {
		super(Cycle.class);
		insert(new Cycle("Классика"));
		insert(new Cycle("Новое"));
	}

	@Override
	public List<Cycle> getAll() {
		Query q = DataManager.getInstance().createQuery("SELECT c FROM Cycle c ORDER BY id");
		List<Cycle> result = q.getResultList();
		return result;
	}
	
	@Override
	public List<Book> getCycle(Cycle cycle) {
		Query q = DataManager.getInstance().createQuery("SELECT c.booksList FROM Cycle c WHERE c.id = :id");
		q.setParameter("id", cycle.getId());
		List<Book> result = q.getResultList();
		return result;
	}
}
