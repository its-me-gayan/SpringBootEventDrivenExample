package org.api.packinglot;

import org.api.packinglot.entity.EventStore;
import org.api.packinglot.repository.EventStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class EventParkingLotConsumerServiceApplicationRunner  {
    public static void main(String[] args) {
        SpringApplication.run(EventParkingLotConsumerServiceApplicationRunner.class, args);
    }
}