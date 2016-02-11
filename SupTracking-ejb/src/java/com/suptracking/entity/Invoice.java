/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptracking.entity;

import com.suptracking.utils.Utils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author EPTR
 */
@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "user_fk")
    public User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float amount;
    private Long timestampDate;

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Long getTimestampDate() {
        return timestampDate;
    }

    public String getDateFromTimestamp() {
        return Utils.getDateOnlyFromTimestamp(this.timestampDate);
    }

    public void setTimestampDate() {
        this.timestampDate = Utils.getCurrentTimestamp();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
