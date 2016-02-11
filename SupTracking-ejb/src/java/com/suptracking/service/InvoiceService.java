/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptracking.service;

import com.suptracking.dao.InvoiceDao;
import com.suptracking.entity.Invoice;
import com.suptracking.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author EPTR
 */
@Stateless
public class InvoiceService {
    @EJB
    private InvoiceDao invoiceDao;

    public Invoice addInvoice(Invoice invoice) {
        return invoiceDao.addInvoice(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoices();
    }

    public Invoice findInvoiceById(String InvoiceId) {
        return invoiceDao.findInvoiceById(InvoiceId);
    }

    public List<Invoice> findInvoicesForUser(User user) {
        return invoiceDao.findInvoicesForUser(user);
    }

    public void updateInvoice(Invoice invoice) {
        invoiceDao.updateInvoice(invoice);
    }

    public void removeInvoice(Invoice invoice) {
        invoiceDao.removeInvoice(invoice);
    }
}
