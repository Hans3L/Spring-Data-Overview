package com.creativity.springdataoverview.repository;

import com.creativity.springdataoverview.entity.FlightEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CrudTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Qualifier("flightRepository")
    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void shouldPerformCRUDOperations(){
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setOrigin("Israel");
        flightEntity.setDestination("Perú");
        flightEntity.setScheduledAt(LocalDateTime.parse("2020-12-13T12:12:05"));
        testEntityManager.persist(flightEntity);
        flightRepository.deleteById(flightEntity.getId());
        Assert.assertEquals(flightRepository.count(), 0);
    }
}
