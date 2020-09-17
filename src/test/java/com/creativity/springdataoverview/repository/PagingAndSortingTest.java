package com.creativity.springdataoverview.repository;

import com.creativity.springdataoverview.entity.FlightEntity;
import javassist.runtime.Desc;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class PagingAndSortingTest {

    @Qualifier("flightRepository")
    @Autowired
    private FlightRepository flightRepository;

    @Before
    public void setUp(){
        flightRepository.deleteAll();
    }

    @Test
    public void shouldSortFlightByScheduledAndThenName(){
        final LocalDateTime now =  LocalDateTime.now();
        FlightEntity london = createFlight("London", now);
        FlightEntity london1 = createFlight("London", now.plusHours(2));
        FlightEntity london2 = createFlight("London", now.minusHours(1));
        FlightEntity tel_Aviv = createFlight("Tel Aviv", now);
        FlightEntity tel_Aviv1 = createFlight("Tel Aviv", now.plusHours(2));
        flightRepository.save(london);
        flightRepository.save(london1);
        flightRepository.save(london2);
        flightRepository.save(tel_Aviv);
        flightRepository.save(tel_Aviv1);


        Iterable<FlightEntity>  flight= flightRepository.findAll(Sort.by("destination","scheduledAt"));
        Iterator<FlightEntity>  iterator = flight.iterator();
        Assertions.assertThat(iterator.next()).isEqualToComparingFieldByField(london2);
        Assertions.assertThat(iterator.next()).isEqualToComparingFieldByField(london);
        Assertions.assertThat(iterator.next()).isEqualToComparingFieldByField(london1);
        Assertions.assertThat(iterator.next()).isEqualToComparingFieldByField(tel_Aviv);
        Assertions.assertThat(iterator.next()).isEqualToComparingFieldByField(tel_Aviv1);

    }

    @Test
    public void shouldSortFlightByDestination(){
        FlightEntity flight = createFlight("London");
        FlightEntity flight1 = createFlight("Austria");
        FlightEntity flight2 = createFlight("Barcelona");
        flightRepository.save(flight);
        flightRepository.save(flight1);
        flightRepository.save(flight2);

        Iterable<FlightEntity> iterable = flightRepository.findAll(Sort.by("destination"));
        Iterator<FlightEntity> iterator = iterable.iterator();

        Assertions.assertThat(iterator.next().getDestination().equals("Austria"));
        Assertions.assertThat(iterator.next().getDestination().equals("Barcelona"));
        Assertions.assertThat(iterator.next().getDestination().equals("London"));
    }

    @Test
    public void shouldPageResults(){
        for (int i=0; i< 50; i++){
            flightRepository.save(createFlight(String.valueOf(i)));
        }
        Page<FlightEntity> page = flightRepository.findAll(PageRequest.of(2,5)); //pasamos 2 para solicitar que estemos en la pag. dos
        Assertions.assertThat(page.getTotalElements()).isEqualTo(50);
        Assertions.assertThat(page.getNumberOfElements()).isEqualTo(5);
        Assertions.assertThat(page.getTotalPages()).isEqualTo(10);
        Assertions.assertThat(page.getContent())
                .extracting(FlightEntity::getDestination)
                .containsExactly("10","11","12","13","14");
    }

    @Test
    public void shouldPageAndSortResults(){
        for (int i=0; i< 50; i++){
            flightRepository.save(createFlight(String.valueOf(i)));
        }
        Page<FlightEntity> page = flightRepository.findAll(PageRequest.of(2,5,Sort.by(Sort.Direction.DESC,"destination")));
        Assertions.assertThat(page.getNumberOfElements()).isEqualTo(5);
        Assertions.assertThat(page.getTotalPages()).isEqualTo(10);
        Assertions.assertThat(page.getContent())
                .extracting(FlightEntity::getDestination)
                .containsExactly("44","43","42","41","40");
    }

    @Test
    public void shouldPageAndSortADerivedQuery(){

        for (int i=0; i< 10; i++){
            FlightEntity flightEntity = createFlight(String.valueOf(i));
            flightEntity.setOrigin("London");
            flightRepository.save(flightEntity);
        }

        for (int i=0; i< 10; i++){
            FlightEntity flightEntity = createFlight(String.valueOf(i));
            flightEntity.setOrigin("Paris");
            flightRepository.save(flightEntity);
        }

        Page<FlightEntity> page = flightRepository
                .findByOrigin("London",
                        PageRequest.of(0,5,Sort.by(Sort.Direction.DESC,"destination")));  //pasamos 0 para solicitar que estemos en la pag. cero
        Assertions.assertThat(page.getNumberOfElements()).isEqualTo(5);
        Assertions.assertThat(page.getTotalPages()).isEqualTo(2);
        Assertions.assertThat(page.getContent())
                .extracting(FlightEntity::getDestination)
                .containsExactly("9","8","7","6","5");
    }

    private FlightEntity createFlight(String destination){
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setOrigin("London");
        flightEntity.setDestination(destination);
        flightEntity.setScheduledAt(LocalDateTime.parse("2020-12-13T12:12:05"));
        return flightEntity;
    }

    private FlightEntity createFlight(String destination, LocalDateTime scheduledAt){
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setOrigin("Perú");
        flightEntity.setDestination(destination);
        flightEntity.setScheduledAt(scheduledAt);
        return flightEntity;
    }
}
