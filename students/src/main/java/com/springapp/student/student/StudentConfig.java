package com.springapp.student.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
           Student jack = new Student( "Jack", "jack.doe@gmail.com",
                    LocalDate.of(2000, Month.APRIL, 23));
            Student luke = new Student("Luke", "luke.scott@gmail.com",
                    LocalDate.of(2001, Month.APRIL, 23));

            repository.saveAll(List.of(jack, luke));
        };
    }
}
