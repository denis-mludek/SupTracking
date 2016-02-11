package com.suptracking.dao.jpa;

import com.suptracking.dao.UserDao;
import com.suptracking.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class JpaUserDao implements UserDao {
    private static final String JPQL_SELECT_BY_USERNAME = "SELECT u FROM User u WHERE u.username=:username";
    private static final String PARAM_USERNAME = "username";
    @PersistenceContext
    private EntityManager em;

    @Override
    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User findUserById(String userId) {
        Query req = em.createQuery(JPQL_SELECT_BY_USERNAME);
        req.setParameter(PARAM_USERNAME, userId);
        User user = null;
        try {
            user = (User) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }
}
