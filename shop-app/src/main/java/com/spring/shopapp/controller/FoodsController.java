package com.spring.shopapp.controller;

import com.spring.shopapp.entity.Foods;
import com.spring.shopapp.repository.FoodsRepository;
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
public class FoodsController {

    @Autowired
    private FoodsRepository foodRepository;

    @RequestMapping(value = "foods/{id}", method = RequestMethod.GET)
    public ResponseEntity<Foods> findFoodById(@PathVariable("id") Long id) {
        Optional<Foods> food = foodRepository.findById(id);

        return food.isPresent() ? new ResponseEntity<>(food.get(), HttpStatus.OK) :
                new ResponseEntity<>(food.get(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "foods", method = RequestMethod.POST)
    public ResponseEntity<Foods> addFoods(@RequestBody Foods food) {
        foodRepository.save(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "foods", method = RequestMethod.PUT)
    public ResponseEntity<Foods> updateFoods(@RequestBody Foods food) {
        foodRepository.save(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "foods/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Foods>> findFoodByName(@PathVariable("name") String foodName) {
        List<Foods> food = foodRepository.findFoodByName(foodName);
        return food.isEmpty() ? new ResponseEntity<>(food, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(food, HttpStatus.OK);
    }

    @RequestMapping(value = "foods/price/{price}", method = RequestMethod.GET)
    public ResponseEntity<Foods> findFoodByPrice(@PathVariable("price") BigDecimal price) {
        Optional<Foods> food = foodRepository.findByPrice(price);
        return food.isPresent() ? new ResponseEntity<>(food.get(), HttpStatus.OK) :
                new ResponseEntity<>(food.get(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "foods/manufacturerName/{manufacturerName}", method = RequestMethod.GET)
    public ResponseEntity<List<Foods>> findFoodByManufacturerName(@PathVariable("manufacturerName") String manufacturerName) {
        List<Foods> food = foodRepository.findByManufacturerName(manufacturerName);
        return food.isEmpty() ? new ResponseEntity<>(food, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(food, HttpStatus.OK);
    }

    @RequestMapping(value = "foods/expiryDate/{expiryDate}", method = RequestMethod.GET)
    public ResponseEntity<List<Foods>> findFoodByExpiryDate(@PathVariable("expiryDate") String expiryDate) {
        List<Foods> food = foodRepository.findByExpiryDate(expiryDate);

        return food.isEmpty() ? new ResponseEntity<>(food, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(food, HttpStatus.OK);
    }

    @RequestMapping(value = "foods", method = RequestMethod.GET)
    public ResponseEntity<List<Foods>> findAllFoods() {
        List<Foods> foods = new ArrayList<>(foodRepository.findAll());
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @RequestMapping(value = "foods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Foods> deleteFoodById(@PathVariable("id") Long id) {
        foodRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
