/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptracking.service;

import com.suptracking.dao.AreaDao;
import com.suptracking.entity.Area;
import com.suptracking.entity.Car;
import com.suptracking.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author EPTR
 */
@Stateless
public class AreaService {
    @EJB
    private AreaDao areaDao;

    public void addArea(Area area) {
        areaDao.addArea(area);
    }

    public Area findAreaById(Long areaId) {
        return areaDao.findAreaById(areaId);
    }

    public List<Area> getAllAreas() {
        return areaDao.getAllAreas();
    }

    public void updateArea(Area area) {
        areaDao.updateArea(area);
    }

    public void removeArea(Long id) {
        areaDao.removeArea(areaDao.findAreaById(id));
    }

    public List<Area> getAreasOfUser(User user) {
        return areaDao.getAreasOfUser(user);
    }

    public List<Area> getAreasOfCar(Car car) {
        return areaDao.getAreasOfCar(car);
    }
}
