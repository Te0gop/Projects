package com.spring.shopapp.repository;

import com.spring.shopapp.entity.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DrinksRepository extends JpaRepository<Drinks, Long> {

}
