package me.geakstr.insapp.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import me.geakstr.insapp.api.rest.Rest;

@ApplicationPath("/")
public class Application extends javax.ws.rs.core.Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(Rest.class);
	}
}
