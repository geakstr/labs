package me.geakstr.insapp.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car implements IEntity {
	@Id
	@Column(nullable = false)
	private String car_num;

	@Column(nullable = false, unique = false)
	private String car_model;
	
	@Column(nullable = false, unique = false)
	private Integer car_power;
	
	@Column(nullable = false, unique = false)
	private String driver_license;

	public String getCar_num() {
		return car_num;
	}

	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public Integer getCar_power() {
		return car_power;
	}

	public void setCar_power(Integer car_power) {
		this.car_power = car_power;
	}

	public String getDriver_license() {
		return driver_license;
	}

	public void setDriver_license(String driver_license) {
		this.driver_license = driver_license;
	}

	@Override
	public String toString() {
		return String.format(
				"Car : car_num = %s; car_model = %s; car_power = %s; driver_license = %s",
				car_num,
				car_model,
				car_power,
				driver_license);
	}
}
