package com.spring.shopapp.controller;

import com.spring.shopapp.entity.Foods;
import com.spring.shopapp.repository.FoodsRepository;
import com.spring.shopapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "foods/{id}", method = RequestMethod.GET)
    public ResponseEntity<Foods> findFoodById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(foodService.getFoodById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "foods", method = RequestMethod.POST)
    public ResponseEntity<Foods> addFoods(@RequestBody Foods food) {
        return foodService.addFood(food);
    }

    @RequestMapping(value = "foods/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Foods> updateFoods(@PathVariable("id") Long id, @RequestBody Foods food) {
        return foodService.updateFood(id, food);
    }

    @RequestMapping(value = "foods/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Foods>> findFoodByName(@PathVariable("name") String foodName) {
        return foodService.findFoodByName(foodName);
    }

    @RequestMapping(value = "foods/price/{price}", method = RequestMethod.GET)
    public ResponseEntity<Foods> findFoodByPrice(@PathVariable("price") BigDecimal price) {
        return foodService.findFoodByPrice(price);
    }

    @RequestMapping(value = "foods/manufacturerName/{manufacturerName}", method = RequestMethod.GET)
    public ResponseEntity<List<Foods>> findFoodByManufacturerName(@PathVariable("manufacturerName") String manufacturerName) {
        return foodService.findFoodByManufacturerName(manufacturerName);
    }

    @RequestMapping(value = "foods/expiryDate/{expiryDate}", method = RequestMethod.GET)
    public ResponseEntity<List<Foods>> findFoodByExpiryDate(@PathVariable("expiryDate") String expiryDate) {
        return foodService.findFoodByExpiryDate(expiryDate);
    }

    @RequestMapping(value = "foods", method = RequestMethod.GET)
    public ResponseEntity<List<Foods>> findAllFoods() {
        return foodService.findAllFoods();
    }

    @RequestMapping(value = "foods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Foods> deleteFoodById(@PathVariable("id") Long id) {
        return foodService.deleteFoodById(id);
    }
}
