package me.geakstr.insapp.dao;

import java.util.List;

import javax.ejb.Stateless;

import me.geakstr.insapp.dao.entities.Driver;

@Stateless
public class DriverDao extends AbstractDao<Driver> {
	public DriverDao() {
		super(Driver.class);
	}

	@SuppressWarnings("unchecked")
	public List<Driver> findAllDrivers() {
		return em.createQuery("SELECT d From Driver d").getResultList();
	}
}
