/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptracking.validators;

import com.suptracking.dao.UserDao;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author EPTR
 */
@ManagedBean
public class UsernameValidator implements Validator {
    private static final String USERNAME_TAKEN = "This username is already taken.";
    @EJB
    private UserDao userDao;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String username = (String) value;
        try {
            if (userDao.findUserById(username) != null) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, USERNAME_TAKEN, null));
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(component.getClientId(facesContext), message);
        }
    }
}
