package com.suptracking.dao.jpa;

import com.suptracking.dao.AreaDao;
import com.suptracking.entity.Area;
import com.suptracking.entity.Car;
import com.suptracking.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class JpaAreaDao implements AreaDao {
    private static final String JPQL_SELECT_BY_ID = "SELECT a FROM Area a WHERE a.id=:id";
    private static final String PARAM_ID = "id";
    private static final String JPQL_SELECT_BY_USER = "SELECT a FROM Area a inner join a.car c WHERE c.user=:user";
    private static final String PARAM_USER = "user";
    private static final String JPQL_SELECT_BY_CAR = "SELECT a FROM Area a WHERE a.car=:car";
    private static final String PARAM_CAR = "car";
    @PersistenceContext
    private EntityManager em;

    @Override
    public Area addArea(Area area) {
        em.persist(area);
        return area;
    }

    @Override
    public List<Area> getAllAreas() {
        return em.createQuery("SELECT a FROM Area a").getResultList();
    }

    @Override
    public Area findAreaById(Long areaId) {
        Query req = em.createQuery(JPQL_SELECT_BY_ID);
        req.setParameter(PARAM_ID, areaId);
        Area area = null;
        try {
            area = (Area) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return area;
    }

    @Override
    public void updateArea(Area area) {
        em.merge(area);
    }

    @Override
    public void removeArea(Area area) {
        em.remove(area);
    }

    @Override
    public List<Area> getAreasOfUser(User user) {
        Query req = em.createQuery(JPQL_SELECT_BY_USER);
        req.setParameter(PARAM_USER, user);
        List<Area> areas = null;
        try {
            areas = req.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return areas;
    }

    @Override
    public List<Area> getAreasOfCar(Car car) {
        Query req = em.createQuery(JPQL_SELECT_BY_CAR);
        req.setParameter(PARAM_CAR, car);
        List<Area> areas = null;
        try {
            areas = req.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return areas;
    }
}
