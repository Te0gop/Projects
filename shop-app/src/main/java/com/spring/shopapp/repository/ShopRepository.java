package com.spring.shopapp.repository;

import com.spring.shopapp.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
