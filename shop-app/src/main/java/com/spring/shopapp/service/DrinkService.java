package com.spring.shopapp.service;

import com.spring.shopapp.entity.Drinks;
import com.spring.shopapp.repository.DrinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {

    @Autowired
    DrinksRepository drinksRepository;

//    @Transactional(readOnly = true)
    public Drinks getDrinkById(Long id) {
        return drinksRepository.getById(id);
    }

    public ResponseEntity<List<Drinks>> getDrinkByName(String name) {
        List<Drinks> drinkName = drinksRepository.findDrinkByName(name);
        return drinkName.isEmpty() ? new ResponseEntity<>(drinkName, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(drinkName, HttpStatus.OK);

    }

    public ResponseEntity<List<Drinks>> findAllDrinks() {
        List<Drinks> drinks = new ArrayList<>(drinksRepository.findAll());
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    public ResponseEntity<Drinks> addDrink(Drinks drink) {
        drinksRepository.save(drink);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Drinks> updateDrink(Drinks drink) {
        drinksRepository.save(drink);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Drinks> findDrinkByPrice(BigDecimal price) {
        Optional<Drinks> drinkPrice = drinksRepository.findByPrice(price);

        return drinkPrice.isPresent() ? new ResponseEntity<>(drinkPrice.get(), HttpStatus.OK) :
                new ResponseEntity<>(drinkPrice.get(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Drinks>> findDrinkByManufacturerName(String manufacturerName) {
        List<Drinks> drink = drinksRepository.findByManufacturerName(manufacturerName);

        return drink.isEmpty() ? new ResponseEntity<>(drink, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(drink, HttpStatus.OK);
    }

    public ResponseEntity<Drinks> findDrinksByAlcoholContent(double alcoholContent) {
        Optional<Drinks> drink = drinksRepository.findByAlcoholContent(alcoholContent);

        return drink.isPresent() ? new ResponseEntity<>(drink.get(), HttpStatus.OK) :
                new ResponseEntity<>(drink.get(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Drinks> deleteDrinkById(Long id) {
        drinksRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
