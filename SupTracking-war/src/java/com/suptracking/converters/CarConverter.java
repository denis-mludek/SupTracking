/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptracking.converters;

import com.suptracking.entity.Car;
import com.suptracking.service.CarService;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author EPTR
 */
@FacesConverter("carConverter")
public class CarConverter implements Converter {
    @EJB
    private CarService carService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Car car = carService.findCarById(Long.valueOf(value.split("=")[1].split(", name")[0]));
        return car;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
