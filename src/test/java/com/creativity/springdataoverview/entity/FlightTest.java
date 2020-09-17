package com.creativity.springdataoverview.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase
public class FlightTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void verifyFlightCanBeSaved(){

        final FlightEntity flightEntity = new FlightEntity();
        flightEntity.setOrigin("Lima");
        flightEntity.setDestination("Jerusalem");
        flightEntity.setScheduledAt(LocalDateTime.parse("2020-12-13T12:12:05"));
        testEntityManager.persist(flightEntity);

        Assertions.assertEquals("Jerusalem", flightEntity.getDestination());
    }
}
