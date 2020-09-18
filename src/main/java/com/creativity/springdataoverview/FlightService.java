package com.creativity.springdataoverview;

import com.creativity.springdataoverview.entity.FlightEntity;
import com.creativity.springdataoverview.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class FlightService {

    @Qualifier("flightRepository")
    @Autowired
    private FlightRepository flightRepository;

    public FlightService(@Qualifier("flightRepository") FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public void saveFlight(FlightEntity flightEntity){
        flightRepository.save(flightEntity);
        throw new RuntimeException("I failed");
    }

    @Transactional
    public void saveFlightTransactional(FlightEntity flightEntity){
        flightRepository.save(flightEntity);
        throw new RuntimeException("I failed");
    }

}
