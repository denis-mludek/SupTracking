/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptracking.controllers;

import com.suptracking.entity.Invoice;
import com.suptracking.entity.Offer;
import com.suptracking.entity.User;
import com.suptracking.service.InvoiceService;
import com.suptracking.service.OfferService;
import com.suptracking.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

/**
 * @author EPTR
 */
@ManagedBean(name = "registerController")
public class RegisterController implements Serializable {
    private User user;
    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    @EJB
    private UserService userService;
    @EJB
    private OfferService offerService;
    @EJB
    private InvoiceService invoiceService;

    public RegisterController() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String register() {
        // If its the first User to register (supposed to be you), Admin = true , create basic offer in DB
        if (userService.getAllUsers().isEmpty()) {
            user.setAdminBool(true);
            Offer basicOffer = new Offer();
            basicOffer.setName("Basic offer");
            basicOffer.setDescription("SUPER DESC OFFER !!");
            basicOffer.setPayementFrequency("monthly");
            basicOffer.setPrice(10);
            offerService.addOffer(basicOffer);
        }
        // Set Basic Offer for every user, since we have only 1 offer... 
        user.setOffer(offerService.findOfferByName("Basic offer"));
        // Create the first invoice and attach it to the user
        Invoice invoice = new Invoice();
        invoice.setAmount(user.getOffer().getPrice());
        invoice.setTimestampDate();
        invoice.setUser(user);
        user.getListInvoices().add(invoice);
        userService.addUser(user);
        loginController.setUser(user);
        return "index?faces-redirect=true";
    }
}
