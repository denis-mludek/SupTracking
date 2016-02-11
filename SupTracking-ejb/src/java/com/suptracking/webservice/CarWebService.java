package com.suptracking.webservice;

import com.suptracking.entity.Car;
import com.suptracking.entity.GpsPosition;
import com.suptracking.service.CarService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author EPTR
 */
@WebService(serviceName = "CarWebService")
@Stateless
public class CarWebService {
    @EJB
    private CarService carService;

    // Client -> App
    @WebMethod(operationName = "updateCarPosition")
    @Oneway
    public void updateCarPosition(@WebParam(name = "latitude") double latitude, @WebParam(name = "longitude") double longitude,
                                  @WebParam(name = "carId") Long carId) {
        Car car = carService.findCarById(carId);
        GpsPosition pos = new GpsPosition();
        pos.setLatitude(latitude);
        pos.setLongitude(longitude);
        car.setUpdateTimestamp();
        car.getListPositions().add(pos);
        carService.updateCar(car);
    }
}
