package me.geakstr.insapp.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver implements IEntity {
	@Id
	@Column(nullable = false)
	private String license;

	@Column(nullable = false)
	private String fio;
	
	@Override
	public String toString() {
		return String.format(
				"Driver : license = %s; fio = %s",
				license,
				fio);
	}
}