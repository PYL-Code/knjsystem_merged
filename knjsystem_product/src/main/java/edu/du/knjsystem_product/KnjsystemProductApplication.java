package edu.du.knjsystem_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KnjsystemProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnjsystemProductApplication.class, args);
    }

}
