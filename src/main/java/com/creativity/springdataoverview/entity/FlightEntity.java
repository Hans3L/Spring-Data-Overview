package com.creativity.springdataoverview.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

public class FlightEntity implements Serializable {

    private static final long serialVersionUID = -864252247544008041L;

    private String id;

    private String origin;

    private String destination;

    private LocalDateTime scheduledAt;

    public FlightEntity() {
    }

    public FlightEntity(String id, String origin, String destination, LocalDateTime scheduledAt) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.scheduledAt = scheduledAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
