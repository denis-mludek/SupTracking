package com.suptracking.controllers;

import com.suptracking.entity.Invoice;
import com.suptracking.entity.User;
import com.suptracking.service.InvoiceService;
import com.suptracking.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;

/**
 * @author EPTR
 */
@ManagedBean
@ViewScoped
public class AdminController implements Serializable {
    private static final String urlManageUsers = "/admin/manageUsers.xhtml?faces-redirect=true";
    private final String viewId;
    @EJB
    private UserService userService;
    @EJB
    private InvoiceService invoiceService;
    private User user;
    private DataModel<User> listUsers;
    private DataModel<Invoice> listInvoices;

    public AdminController() {
        this.viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        this.user = null;
        this.listUsers = null;
    }

    @PostConstruct
    private void init() {
        if (viewId.contains("addUser")) {
            user = new User();
        } else if (viewId.contains("editUser")) {
            String paramUsername = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            user = userService.findUserById(paramUsername);
        } else if (viewId.contains("manageUsers")) {
            listUsers = new ListDataModel(userService.getAllUsers());
        } else if (viewId.contains("seeInvoices")) {
            listInvoices = new ListDataModel(invoiceService.getAllInvoices());
        }
    }

    public String addUser() {
        userService.addUser(user);
        return urlManageUsers;
    }

    public String editUser() {
        userService.updateUser(user);
        System.out.print("EDIIIT :" + user);
        return urlManageUsers;
    }

    public String removeUser() {
        System.out.print("DELETED USER : " + listUsers.getRowData().getUsername());
        userService.removeUser(listUsers.getRowData().getUsername());
        return urlManageUsers;
    }

    public User getUser() {
        return user;
    }

    public DataModel<User> getListUsers() {
        return listUsers;
    }

    public DataModel<Invoice> getListInvoices() {
        return listInvoices;
    }
}
