package ru.aspu.javaee.lab5.datamanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class DataManager {
	@PersistenceUnit
	private static EntityManager em = null;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Library");

	public static EntityManager getInstance() {
		if ((em == null) || (!em.isOpen())) {
			em = emf.createEntityManager();
		}

		return em;
	}
}
