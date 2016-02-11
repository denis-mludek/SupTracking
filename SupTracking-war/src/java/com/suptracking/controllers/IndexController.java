package com.suptracking.controllers;

import com.suptracking.service.CarService;
import com.suptracking.service.UserService;
import com.suptracking.utils.Utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;

/**
 * @author EPTR
 */
@ManagedBean(name = "indexController")
@RequestScoped
public class IndexController implements Serializable {
    private static final String urlIndexRedirect = "/index?faces-redirect=true";
    private static final String urlMyProfile = "/user/myProfile?faces-redirect=true";
    private static final String urlLogin = "/login";
    @EJB
    private UserService userService;
    @EJB
    private CarService carService;
    private DataModel listCars;
    private int nbUsers;
    private int nbCars;

    public IndexController() {
        nbCars = 0;
        nbUsers = 0;
        listCars = null;
    }

    @PostConstruct
    private void init() {
        nbCars = carService.getAllCars().size();
        nbUsers = userService.getAllUsers().size();
        if (Utils.getUserInSession() != null) {
            listCars = new ListDataModel(carService.getCarsOfUser(Utils.getUserInSession()));
        }
    }

    public int getNbUsers() {
        return nbUsers;
    }

    public int getNbCars() {
        return nbCars;
    }

    public DataModel getListCars() {
        return listCars;
    }
}
