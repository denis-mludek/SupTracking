package com.suptracking.entity;

import com.suptracking.utils.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull(message = "{user_field_notnull}")
    @Size(min = 3, message = "{user_username_size}")
    @Column(name = "username")
    private String username;
    @NotNull(message = "{user_field_notnull}")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull(message = "{user_field_notnull}")
    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", message = "{user_email_pattern}")
    @Column(name = "email_address")
    private String email;
    @NotNull(message = "{user_field_notnull}")
    @Column(name = "firstname")
    private String firstname;
    @NotNull(message = "{user_field_notnull}")
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "credit_card_number")
    @NotNull(message = "{user_field_notnull}")
    @Pattern(regexp = "^[0-9]{16}", message = "{user_creditCardNumber_pattern}")
    private String creditCardNumber;
    @NotNull(message = "{user_field_notnull}")
    @Size(min = 6, message = "{user_password_size}")
    @Column(name = "password")
    private String password;
    @Column(name = "admin_bool")
    private boolean adminBool;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gps_pos")
    private GpsPosition gpsPos;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Invoice> listInvoices;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> listCars;
    @OneToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    @Column(name = "update_timestamp")
    private Long updateTimestamp;

    /**
     * Constructor
     */
    public User() {
        this.username = null;
        this.adminBool = false;
        this.listInvoices = new ArrayList<>();
        this.listCars = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdminBool() {
        return adminBool;
    }

    public void setAdminBool(boolean adminBool) {
        this.adminBool = adminBool;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Invoice> getListInvoices() {
        return listInvoices;
    }

    public void setListInvoices(List<Invoice> listInvoices) {
        this.listInvoices = listInvoices;
    }

    public List<Car> getListCars() {
        return listCars;
    }

    public void setListCars(List<Car> listCars) {
        this.listCars = listCars;
    }

    public GpsPosition getGpsPos() {
        return gpsPos;
    }

    public void setGpsPos(GpsPosition gpsPos) {
        this.gpsPos = gpsPos;
    }

    public Long getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp() {
        this.updateTimestamp = Utils.getCurrentTimestamp();
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", phoneNumber=" + phoneNumber + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", creditCardNumber=" + creditCardNumber + ", password=" + password + ", adminBool=" + adminBool + ", offer=" + offer + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
}