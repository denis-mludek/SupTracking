/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptracking.controllers;

import com.suptracking.entity.Car;
import com.suptracking.entity.GpsPosition;
import com.suptracking.service.CarService;
import com.suptracking.utils.Utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;

/**
 * @author EPTR
 */
@ManagedBean
@ViewScoped
public class CarController implements Serializable {
    private static final String urlManageCars = "/user/manageCars.xhtml?faces-redirect=true";
    private final String viewId;
    private Long idCarToEdit;
    private Car car;
    private DataModel<Car> listCars;
    private double latitude;
    private double longitude;
    @EJB
    private CarService carService;

    public CarController() {
        viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        car = null;
        listCars = null;
    }

    @PostConstruct
    private void init() {
        if (viewId.contains("formCar")) {
            String paramId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            idCarToEdit = Utils.convertStringToLong(paramId);
            if (idCarToEdit == null) {
                car = new Car();
            } else {
                car = carService.findCarById(idCarToEdit);
                int size = car.getListPositions().size() - 1;
                latitude = car.getListPositions().get(size).getLatitude();
                longitude = car.getListPositions().get(size).getLongitude();
            }
        } else if (viewId.contains("manageCars")) {
            listCars = new ListDataModel(carService.getCarsOfUser(Utils.getUserInSession()));
        }
    }

    public Car getCar() {
        return car;
    }

    public DataModel<Car> getListCars() {
        return listCars;
    }

    public Long getIdCarToEdit() {
        return idCarToEdit;
    }

    public void setIdCarToEdit(Long idCarToEdit) {
        this.idCarToEdit = idCarToEdit;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String addCar() {
        car.setUser(Utils.getUserInSession());
        car.getListPositions().add(new GpsPosition(car, latitude, longitude));
        carService.addCar(car);
        return urlManageCars;
    }

    public String editCar() {
        int sizeList = car.getListPositions().size() - 1;
        if (car.getListPositions().get(sizeList).getLatitude() != latitude || car.getListPositions().get(sizeList).getLongitude() != longitude) {
            car.getListPositions().add(new GpsPosition(car, latitude, longitude));
        }
        carService.updateCar(car);
        return urlManageCars;
    }

    public String removeCar() {
        carService.removeCar(listCars.getRowData().getId());
        return urlManageCars;
    }
}
