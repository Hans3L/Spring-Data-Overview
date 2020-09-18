package com.creativity.springdataoverview;

import com.creativity.springdataoverview.entity.FlightEntity;
import com.creativity.springdataoverview.repository.FlightRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTest {

    @Autowired
    private FlightService flightService;

    @Qualifier("flightRepository")
    @Autowired
    private FlightRepository flightRepository;

    @Before
    public void setUp(){
        flightRepository.deleteAll();
    }

    //shouldNotRollbackWhenTheresNoTransaction
    @Test
    public void noDebeRevertirseCuandoNoHayTransacción(){
        try {
            flightService.saveFlight(new FlightEntity());
        } catch (Exception e){
            // Hacer nada - Do nothing
            System.out.println(e);
            System.out.println(flightRepository.findAll()); //Guarda a pesar de una Excepcion
        }
        finally {
            Assertions.assertThat(flightRepository.findAll()).isNotEmpty();
            Assertions.assertThat(flightRepository.findAll()).hasSize(1); //existe un valor vacio en la lista
        }
    }

    @Test
    public void noDebeRevertirseCuandoSiHayTransacción(){
        try {
            flightService.saveFlightTransactional(new FlightEntity());
        } catch (Exception e){
            // Hacer nada - Do nothing
            System.out.println(e);
            System.out.println(flightRepository.findAll()); ////No Guarda por que existe una Excepcion
        }
        finally {
            Assertions.assertThat(flightRepository.findAll()).isEmpty();
        }
    }
}
