package com.spring.shopapp.repository;

import com.spring.shopapp.entity.Drinks;
import com.spring.shopapp.entity.Foods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FoodsRepository extends JpaRepository<Foods, Long> {
    List<Foods> findFoodByName(String name);

    List<Foods> findByExpiryDate(String expiryDate);

    List<Foods> findByManufacturerName(String name);

    Optional<Foods> findByPrice(BigDecimal price);
}
