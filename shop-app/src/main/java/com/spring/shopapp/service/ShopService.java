package com.spring.shopapp.service;

import com.spring.shopapp.entity.Drinks;
import com.spring.shopapp.entity.Foods;
import com.spring.shopapp.entity.Shop;
import com.spring.shopapp.repository.DrinksRepository;
import com.spring.shopapp.repository.FoodsRepository;
import com.spring.shopapp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    private DrinksRepository drinksRepository;

    @Autowired
    private FoodsRepository foodsRepository;

    public ResponseEntity<Shop> createShop(Shop shop) {
        shopRepository.save(shop);

        for(Foods food : shop.getFoods()) {
            food.setShop(shop);
            foodsRepository.save(food);
        }

        for(Drinks drink : shop.getDrinks()) {
            drink.setShop(shop);
            drinksRepository.save(drink);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
