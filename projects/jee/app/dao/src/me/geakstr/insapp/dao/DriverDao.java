package me.geakstr.insapp.dao;

import javax.ejb.Stateless;

import me.geakstr.insapp.dao.entities.Driver;

@Stateless
public class DriverDao extends AbstractDao<Driver> {
	public DriverDao() {
		super(Driver.class);
	}	
}
