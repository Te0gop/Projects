package com.spring.shopapp.service;

import com.spring.shopapp.entity.Drinks;
import com.spring.shopapp.entity.Foods;
import com.spring.shopapp.entity.Shop;
import com.spring.shopapp.repository.DrinksRepository;
import com.spring.shopapp.repository.FoodsRepository;
import com.spring.shopapp.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private static Logger LOGGER = LoggerFactory.getLogger(DrinkService.class);

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    private DrinksRepository drinksRepository;

    @Autowired
    private FoodsRepository foodsRepository;

    public ResponseEntity<Shop> createShop(Shop shop) {
        LOGGER.warn("Creating new shop: " + shop.getShopName());
        shopRepository.save(shop);

        shop.setFoods(foodsRepository.findAll());
        shop.setDrinks(drinksRepository.findAll());

        for(Foods food : shop.getFoods()) {
            food.setShop(shop);
            foodsRepository.save(food);
        }
        for(Drinks drink : shop.getDrinks()) {
            drink.setShop(shop);
            drinksRepository.save(drink);
        }
        LOGGER.info("Shop created.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
