package com.creativity.springdataoverview.repository;

import com.creativity.springdataoverview.entity.FlightEntity;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CustomImplTest {

    @Qualifier("flightRepository")
    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void shouldSaveCustomImpl(){
        FlightEntity toDelete = createFlight("London");
        FlightEntity toKeep = createFlight("Paris");
        flightRepository.save(toDelete);
        flightRepository.save(toKeep);
        flightRepository.deleteByOrigin("London");
        //afirmamos que
        Assertions.assertThat(flightRepository.findAll().iterator().next().getOrigin()).isEqualTo("Paris");
    }

    private FlightEntity createFlight(String origin){
        FlightEntity flight = new FlightEntity();
        flight.setOrigin(origin);
        flight.setDestination("Perú");
        flight.setScheduledAt(LocalDateTime.parse("2020-12-13T12:12:05"));
        return flight;
    }
}
