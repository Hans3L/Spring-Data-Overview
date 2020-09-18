package com.creativity.springdataoverview.repository;

import com.creativity.springdataoverview.entity.FlightEntity;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
@AutoConfigureDataMongo
public class DerivedQueryTests {

    @Qualifier("flightRepository")
    @Autowired
    private FlightRepository flightRepository;

    @Before
    public void SetUp(){
        flightRepository.deleteAll();
    }

    @Test
    public void shouldFindFlightsFromLondon(){
        FlightEntity flightEntity = createFlight("London");
        FlightEntity flightEntity1 = createFlight("Berlin");
        flightRepository.save(flightEntity);
        List<FlightEntity> flightEntities =  flightRepository.findByOrigin(flightEntity.getOrigin());
        Assert.assertEquals(flightEntities.get(0).getOrigin(), flightEntity.getOrigin());
    }

    @Test
    public void shouldFindFlightsFromLondonToParis(){
        FlightEntity flightEntity = createFlight1("London", "Israel");
        FlightEntity flightEntity1 = createFlight1("PerÚ","Berlin");
        FlightEntity flightEntity2 = createFlight1("PerÚ","Israel");
        flightRepository.save(flightEntity);
        flightRepository.save(flightEntity1);
        flightRepository.save(flightEntity2);
        List<FlightEntity> peruToIsrael =  flightRepository
                .findByOriginAndDestination("PerÚ","Israel");
        Assertions.assertThat(peruToIsrael).hasSize(1).first().isEqualToComparingFieldByField(flightEntity2);
    }

    @Test
    public void shouldFindFlightsFromLondonOrParis(){
        FlightEntity flightEntity = createFlight1("Tokio", "Tel Aviv");
        FlightEntity flightEntity1 = createFlight1("London","Perú");
        flightRepository.save(flightEntity);
        flightRepository.save(flightEntity1);
        List<FlightEntity> londonOrTokio =  flightRepository
                .findByOriginIn("London","Tokio");
        //Assertions.assertThat(londonOrTokio).hasSize(1);
        Assertions.assertThat(londonOrTokio.get(0)).isEqualToComparingFieldByField(flightEntity);
        Assertions.assertThat(londonOrTokio.get(1)).isEqualToComparingFieldByField(flightEntity1);
    }

    @Test
    public void shouldFindFlightsFromLondonIgnoringCase(){
        FlightEntity flightEntity = createFlight("TOKIO");
        flightRepository.save(flightEntity);
        List<FlightEntity> flightEntities = flightRepository.findByOriginIgnoreCase("TOKIO");
        Assert.assertEquals(flightEntities.get(0).getOrigin(),flightEntity.getOrigin());

    }

    private FlightEntity createFlight1(String origin, String destination){
        FlightEntity flight = new FlightEntity();
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setScheduledAt(LocalDateTime.parse("2020-12-13T12:12:05"));
        return flight;
    }

    private FlightEntity createFlight(String origin){
        FlightEntity flight = new FlightEntity();
        flight.setOrigin(origin);
        flight.setDestination("Brasil");
        flight.setScheduledAt(LocalDateTime.parse("2020-12-13T12:12:05"));
        return flight;
    }
}
