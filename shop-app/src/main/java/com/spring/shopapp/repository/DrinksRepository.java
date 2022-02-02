package com.spring.shopapp.repository;

import com.spring.shopapp.entity.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DrinksRepository extends JpaRepository<Drinks, Long> {
    List<Drinks> findDrinkByName(String name);

    Optional<Drinks> findByPrice(BigDecimal price);

    List<Drinks> findByManufacturerName(String name);

    Optional<Drinks> findByAlcoholContent(double alcoholContent);
}
