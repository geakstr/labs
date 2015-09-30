package me.geakstr.insapp.dao;

import javax.ejb.Stateless;

import me.geakstr.insapp.dao.entities.Car;

@Stateless
public class CarDao extends AbstractDao<Car> {
	public CarDao() {
		super(Car.class);
	}	
}
