package com.suptracking.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author EPTR
 */
@Entity
@Table(name = "areas")
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @NotNull(message = "{user_field_notnull}")
    private GpsPosition gpsPos;
    @Column(name = "hour_start")
    @NotNull(message = "{user_field_notnull}")
    private Long hourStart;
    @Column(name = "hour_end")
    @NotNull(message = "{user_field_notnull}")
    private Long hourEnd;
    @Column(name = "radius_meter")
    @NotNull(message = "{user_field_notnull}")
    private int radiusMeter;
    @ManyToOne
    @JoinColumn(name = "car_fk")
    private Car car;

    public Area() {
        this.gpsPos = new GpsPosition();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHourStart() {
        return hourStart;
    }

    public void setHourStart(Long hourStart) {
        this.hourStart = hourStart;
    }

    public Long getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(Long hourEnd) {
        this.hourEnd = hourEnd;
    }

    public int getRadiusMeter() {
        return radiusMeter;
    }

    public void setRadiusMeter(int radiusMeter) {
        this.radiusMeter = radiusMeter;
    }

    public GpsPosition getGpsPos() {
        return gpsPos;
    }

    public void setGpsPos(GpsPosition gpsPos) {
        this.gpsPos = gpsPos;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        this.gpsPos.setCar(car);
    }

    @Override
    public String toString() {
        return "Area{" + "id=" + id + ", hourStart=" + hourStart + ", hourEnd=" + hourEnd + ", radiusMeter=" + radiusMeter + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Area other = (Area) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
