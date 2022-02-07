package com.spring.bikesapp.repositories;

import com.spring.bikesapp.models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
}
