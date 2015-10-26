package me.geakstr.insapp.web.converters;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import me.geakstr.insapp.business.facades.EmployeeCarFacade;
import me.geakstr.insapp.dao.entities.Car;

@ManagedBean
@ViewScoped
public class CarConverter implements Converter {
    @EJB
    private EmployeeCarFacade carFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return carFacade.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Car) value).getCar_num();
    }
}