package com.creativity.springdataoverview.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


import java.time.LocalDateTime;

@DataMongoTest
@AutoConfigureDataMongo
public class FlightTest {

    @Test
    public void verifyFlightCanBeSaved(){

        final FlightEntity flightEntity = new FlightEntity();
        flightEntity.setOrigin("Lima");
        flightEntity.setDestination("Jerusalem");
        flightEntity.setScheduledAt(LocalDateTime.parse("2020-12-13T12:12:05"));

        Assertions.assertEquals("Jerusalem", flightEntity.getDestination());
    }
}
