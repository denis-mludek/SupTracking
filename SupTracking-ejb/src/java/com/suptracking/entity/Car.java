package com.suptracking.entity;

import com.suptracking.utils.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author EPTR
 */
@Entity
@Table(name = "cars")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "user_fk")
    public User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    @NotNull(message = "{user_field_notnull}")
    private String name;
    @Column(name = "brand")
    @NotNull(message = "{user_field_notnull}")
    private String brand;
    @Column(name = "year_entry_service")
    @Pattern(regexp = "^\\d{4}$", message = "{car_year_pattern}")
    private String yearEntryInService;
    @Column(name = "update_timestamp")
    private Long updateTimestamp;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<GpsPosition> listPositions;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Area> listAreas;

    public Car() {
        listPositions = new ArrayList<>();
        listAreas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearEntryInService() {
        return yearEntryInService;
    }

    public void setYearEntryInService(String yearEntryInService) {
        this.yearEntryInService = yearEntryInService;
    }

    public List<GpsPosition> getListPositions() {
        return listPositions;
    }

    public void setListPositions(List<GpsPosition> listPositions) {
        this.listPositions = listPositions;
    }

    public List<Area> getListArea() {
        return listAreas;
    }

    public void setListArea(List<Area> listArea) {
        this.listAreas = listArea;
    }

    public Long getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp() {
        this.updateTimestamp = Utils.getCurrentTimestamp();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateOnlyLastUpdate() {
        return Utils.getDateOnlyFromTimestamp(this.updateTimestamp);
    }

    public String getDateTimeLastUpdate() {
        return Utils.getDateTimeFromTimestamp(this.updateTimestamp);
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", name=" + name + ", brand=" + brand + ", yearEntryInService=" + yearEntryInService + ", updateTimestamp=" + updateTimestamp + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Car other = (Car) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
