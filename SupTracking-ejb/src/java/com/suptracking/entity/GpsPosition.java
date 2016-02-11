package com.suptracking.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author EPTR
 */
@Entity
@Table(name = "gps_positions")
public class GpsPosition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_fk")
    private Car car;
    @OneToOne(mappedBy = "gpsPos")
    private User user;
    @OneToOne(mappedBy = "gpsPos")
    private Area area;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;

    public GpsPosition() {
        this.car = null;
        this.user = null;
        this.area = null;
    }

    public GpsPosition(Car car, double latitude, double longitude) {
        this.car = car;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GpsPosition(User user, double latitude, double longitude) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GpsPosition(Area area, double latitude, double longitude) {
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "GpsPosition{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }
}
