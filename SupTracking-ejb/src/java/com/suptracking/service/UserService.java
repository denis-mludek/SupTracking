package com.suptracking.service;

import com.suptracking.dao.UserDao;
import com.suptracking.dao.jpa.JpaUserDao;
import com.suptracking.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author YemYem
 */
@Stateless
public class UserService {
    @EJB
    PasswordService passService;
    @EJB
    private UserDao userDao;

    public void addUser(User user) {
        try {
            user.setPassword(passService.encrypt(user.getPassword()));
        } catch (Exception ex) {
            Logger.getLogger(JpaUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        userDao.addUser(user);
    }

    public User findUserById(String userId) {
        return userDao.findUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void updateUser(User user) {
        try {
            user.setPassword(passService.encrypt(user.getPassword()));
        } catch (Exception ex) {
            Logger.getLogger(JpaUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        userDao.updateUser(user);
    }

    public void removeUser(String id) {
        userDao.removeUser(userDao.findUserById(id));
    }

    public User authenticate(String username, String password) {
        User user = new User();
        user.setUsername(username);
        try {
            user.setPassword(passService.encrypt(password));
        } catch (Exception ex) {
            Logger.getLogger(JpaUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        User userFound = userDao.findUserById(user.getUsername());
        if (userFound != null && userFound.getPassword().equals(user.getPassword())) {
            return userFound;
        }
        return null;
    }
}
