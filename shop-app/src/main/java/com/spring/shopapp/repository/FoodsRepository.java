package com.spring.shopapp.repository;

import com.spring.shopapp.entity.Drinks;
import com.spring.shopapp.entity.Foods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodsRepository extends JpaRepository<Foods, Long> {
    List<Foods> findFoodByName(String name);

    List<Foods> findByPrice(BigDecimal price);

    List<Foods> findByManufacturerName(String manufacturerName);

    List<Foods> findByExpiryDate(String expiryDate);
}
