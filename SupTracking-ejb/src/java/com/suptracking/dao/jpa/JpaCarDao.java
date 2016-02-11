package com.suptracking.dao.jpa;

import com.suptracking.dao.CarDao;
import com.suptracking.entity.Car;
import com.suptracking.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class JpaCarDao implements CarDao {
    private static final String JPQL_SELECT_BY_ID = "SELECT c FROM Car c WHERE c.id=:id";
    private static final String PARAM_ID = "id";
    private static final String JPQL_SELECT_BY_USER = "SELECT c FROM Car c WHERE c.user=:user ORDER BY c.updateTimestamp DESC";
    private static final String PARAM_USER = "user";
    @PersistenceContext
    private EntityManager em;

    @Override
    public Car addCar(Car car) {
        em.persist(car);
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        return em.createQuery("SELECT c FROM Car c").getResultList();
    }

    @Override
    public Car findCarById(Long carId) {
        Query req = em.createQuery(JPQL_SELECT_BY_ID);
        req.setParameter(PARAM_ID, carId);
        Car car = null;
        try {
            car = (Car) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return car;
    }

    @Override
    public void updateCar(Car car) {
        em.merge(car);
    }

    @Override
    public void removeCar(Car car) {
        car = em.merge(car);
        em.remove(car);
    }

    @Override
    public List<Car> getCarsOfUser(User user) {
        Query req = em.createQuery(JPQL_SELECT_BY_USER);
        req.setParameter(PARAM_USER, user);
        List<Car> cars = null;
        try {
            cars = req.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.out.println("Error in JpaCarDao class, function : getCarsOfUser. Exception :" + e);
        }
        return cars;
    }
}