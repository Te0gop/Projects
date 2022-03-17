package com.spring.shopapp.service;
import com.spring.shopapp.entity.Foods;
import com.spring.shopapp.repository.FoodsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    FoodsRepository foodRepository;

    public Foods getFoodById(Long id) {
        return foodRepository.getById(id);
    }

    public ResponseEntity<Foods> addFood(Foods food) {
        foodRepository.save(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Foods> updateFood(Long id, Foods food) {
        Optional<Foods> existingFood = foodRepository.findById(id);

        if(existingFood.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(food, existingFood.get(), "id");
        foodRepository.save(existingFood.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Foods>> findFoodByName(String foodName) {
        List<Foods> food = foodRepository.findFoodByName(foodName);
        return food.isEmpty() ? new ResponseEntity<>(food, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(food, HttpStatus.OK);
    }

    public ResponseEntity<Foods> findFoodByPrice(BigDecimal price) {
        Optional<Foods> food = foodRepository.findByPrice(price);
        return food.isPresent() ? new ResponseEntity<>(food.get(), HttpStatus.OK) :
                new ResponseEntity<>(food.get(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Foods>> findFoodByManufacturerName(String manufacturerName) {
        List<Foods> food = foodRepository.findByManufacturerName(manufacturerName);
        return food.isEmpty() ? new ResponseEntity<>(food, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(food, HttpStatus.OK);
    }

    public ResponseEntity<List<Foods>> findFoodByExpiryDate(String expiryDate) {
        List<Foods> food = foodRepository.findByExpiryDate(expiryDate);

        return food.isEmpty() ? new ResponseEntity<>(food, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(food, HttpStatus.OK);
    }
    public ResponseEntity<List<Foods>> findAllFoods() {
        List<Foods> foods = new ArrayList<>(foodRepository.findAll());
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    public ResponseEntity<Foods> deleteFoodById(Long id) {
        foodRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
