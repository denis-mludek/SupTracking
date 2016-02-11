/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptracking.service;

import com.suptracking.dao.CarDao;
import com.suptracking.entity.Car;
import com.suptracking.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author EPTR
 */
@Stateless
public class CarService {
    @EJB
    private CarDao carDao;

    public void addCar(Car car) {
        car.setUpdateTimestamp();
        carDao.addCar(car);
    }

    public Car findCarById(Long carId) {
        return carDao.findCarById(carId);
    }

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    public void removeCar(Long id) {
        carDao.removeCar(carDao.findCarById(id));
    }

    public List<Car> getCarsOfUser(User user) {
        return carDao.getCarsOfUser(user);
    }
}
