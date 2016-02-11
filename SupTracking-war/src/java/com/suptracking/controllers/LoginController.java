package com.suptracking.controllers;

import com.suptracking.entity.User;
import com.suptracking.service.UserService;
import com.suptracking.utils.Utils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * @author EPTR
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {
    private static final String urlIndexRedirect = "/index?faces-redirect=true";
    private static final String urlMyProfile = "/user/myProfile?faces-redirect=true";
    private static final String urlLogin = "/login";
    private User user;
    private String username;
    private String password;
    @EJB
    private UserService userService;

    public LoginController() {
        user = null;
        username = "";
        password = "";
    }

    public String login() {
        setUser(userService.authenticate(username, password));
        if (user != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("#{login_success}"));
            return urlIndexRedirect;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please try again!"));
            Utils.invalidateSession();
            return urlLogin;
        }
    }

    public String logout() {
        Utils.invalidateSession();
        return urlIndexRedirect;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public String updateProfile() {
        userService.updateUser(user);
        Utils.setUserInSession(user);
        return urlMyProfile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        Utils.setUserInSession(user);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserService getUserService() {
        return userService;
    }
}
