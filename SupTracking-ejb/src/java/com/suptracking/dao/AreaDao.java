package com.suptracking.dao;

import com.suptracking.entity.Area;
import com.suptracking.entity.Car;
import com.suptracking.entity.User;

import javax.ejb.Local;
import java.util.List;

/**
 * @author EPTR
 */
@Local
public interface AreaDao {
    public Area addArea(Area area);

    public List<Area> getAllAreas();

    public List<Area> getAreasOfUser(User user);

    public List<Area> getAreasOfCar(Car car);

    public Area findAreaById(Long areaId);

    public void updateArea(Area area);

    public void removeArea(Area area);
}
