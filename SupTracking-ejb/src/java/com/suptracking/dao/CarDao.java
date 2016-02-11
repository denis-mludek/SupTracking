package com.suptracking.dao;

import com.suptracking.entity.Car;
import com.suptracking.entity.User;

import javax.ejb.Local;
import java.util.List;

/**
 * @author EPTR
 */
@Local
public interface CarDao {
    public Car addCar(Car car);

    public List<Car> getAllCars();

    public Car findCarById(Long carId);

    public List<Car> getCarsOfUser(User user);

    public void updateCar(Car car);

    public void removeCar(Car car);
}
