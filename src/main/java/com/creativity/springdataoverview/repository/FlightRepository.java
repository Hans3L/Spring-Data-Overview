package com.creativity.springdataoverview.repository;

import com.creativity.springdataoverview.entity.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("flightRepository")
public interface FlightRepository extends PagingAndSortingRepository<FlightEntity,Long>, CustomerDeleteOrigin {

    List<FlightEntity> findByOrigin(String origin);

    List<FlightEntity> findByOriginAndDestination(String origin, String destination);

    List<FlightEntity> findByOriginIn(String ... origins);

    List<FlightEntity> findByOriginIgnoreCase(String origin);

    Page<FlightEntity> findByOrigin(String origin, Pageable page);
}
