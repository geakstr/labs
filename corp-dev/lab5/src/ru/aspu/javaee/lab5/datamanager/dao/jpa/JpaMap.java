package ru.aspu.javaee.lab5.datamanager.dao.jpa;

import java.util.HashMap;
import java.util.Map;

import ru.aspu.javaee.lab5.datamanager.dao.generics.GenericDaoJpa;

public class JpaMap {
	private final static Map<Type, GenericDaoJpa> jpas;

	static {
		try {
			jpas = new HashMap<Type, GenericDaoJpa>();
			jpas.put(Type.BOOK, new BookDaoJpa());
			jpas.put(Type.CYCLE, new CycleDaoJpa());
			jpas.put(Type.USER, new UserDaoJpa());
			jpas.put(Type.COMMENT, new CommentDaoJpa());
		} catch (Throwable t) {
			throw t;
		}
	}

	public static enum Type {
		BOOK, CYCLE, USER, COMMENT
	}

	public static GenericDaoJpa get(Type type) {
		return jpas.get(type);
	}
}
