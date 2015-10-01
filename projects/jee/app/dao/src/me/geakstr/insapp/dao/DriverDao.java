package me.geakstr.insapp.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import me.geakstr.insapp.dao.entities.Driver;

@Stateless
@LocalBean
public class DriverDao extends AbstractDao<Driver> {
	public DriverDao() {
		super(Driver.class);
	}	
}
