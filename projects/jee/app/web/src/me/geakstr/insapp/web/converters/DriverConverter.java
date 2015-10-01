package me.geakstr.insapp.web.converters;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import me.geakstr.insapp.business.facades.EmployeeDriverFacade;
import me.geakstr.insapp.dao.entities.Driver;

@ManagedBean
@ViewScoped
public class DriverConverter implements Converter {
    @EJB
    private EmployeeDriverFacade driverFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return driverFacade.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Driver) value).getLicense();
    }
}