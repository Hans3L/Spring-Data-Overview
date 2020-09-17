package com.creativity.springdataoverview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_FLIGHT")
public class FlightEntity implements Serializable {

    private static final long serialVersionUID = -864252247544008041L;

    @Id
    @GeneratedValue
    private Long id;

    private String origin;

    private String destination;

    private LocalDateTime scheduledAt;

    public FlightEntity() {
    }

    public FlightEntity(Long id, String origin, String destination, LocalDateTime scheduledAt) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.scheduledAt = scheduledAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", scheduledAt=" + scheduledAt +
                '}';
    }
}
