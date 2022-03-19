package com.spring.shopapp.service;

import com.spring.shopapp.entity.Drinks;
import com.spring.shopapp.repository.DrinksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DrinkService {

    private static Logger LOGGER = LoggerFactory.getLogger(DrinkService.class);

    @Autowired
    DrinksRepository drinksRepository;

    public ResponseEntity<Drinks> getDrinkById(Long id) {
        LOGGER.debug("Searching drink with id: " + id);

        Optional<Drinks> drink = drinksRepository.findById(id);

        if(drink.isEmpty()) {
            LOGGER.error("Drink not found.");
            return new ResponseEntity<>(drink.get(), HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Drink with id: " + id + " was found successfully.");
        return new ResponseEntity<>(drink.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Drinks>> getDrinkByName(String name) {
        LOGGER.debug("Searching drink with name: " + name);

        List<Drinks> drinkName = drinksRepository.findDrinkByName(name);

        if(drinkName.isEmpty()) {
            LOGGER.error("Drink not found.");
            new ResponseEntity<>(drinkName, HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Drink with name: " + name + " was found successfully.");
        return new ResponseEntity<>(drinkName, HttpStatus.OK);
    }

    public ResponseEntity<List<Drinks>> findAllDrinks() {
        LOGGER.debug("Searching all drinks...");
        List<Drinks> drinks = new ArrayList<>(drinksRepository.findAll());
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    public ResponseEntity<Drinks> addDrink(Drinks drink) {
        drinksRepository.save(drink);
        LOGGER.info(drink + " was saved successfully.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Drinks> updateDrink(Drinks drink, Long id) {
        Optional<Drinks> existingDrink = drinksRepository.findById(id);

        if(existingDrink.isEmpty()) {
            LOGGER.error("Drink not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(drink, existingDrink.get(), "id");
        drinksRepository.save(existingDrink.get());
        LOGGER.info(drink + " was updated successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Drinks> findDrinkByPrice(BigDecimal price) {
        LOGGER.debug("Searching drink with price: " + price);
        Optional<Drinks> drinkPrice = drinksRepository.findByPrice(price);

        if(drinkPrice.isEmpty()) {
            LOGGER.error("Drink not found.");
            return new ResponseEntity<>(drinkPrice.get(), HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Drink with price: " + price + " was found successfully.");
        return  new ResponseEntity<>(drinkPrice.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Drinks>> findDrinkByManufacturerName(String manufacturerName) {
        LOGGER.debug("Searching drink with manufacturer name: " + manufacturerName);
        List<Drinks> drink = drinksRepository.findByManufacturerName(manufacturerName);

        if(drink.isEmpty()) {
            LOGGER.error("Drink not found.");
            return new ResponseEntity<>(drink, HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Drink with manufacturerName: " + manufacturerName + " was found successfully.");
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    public ResponseEntity<Drinks> findDrinksByAlcoholContent(double alcoholContent) {
        LOGGER.debug("Searching drink with alcohol content: " + alcoholContent);
        Optional<Drinks> drink = drinksRepository.findByAlcoholContent(alcoholContent);

        if(drink.isEmpty()) {
            LOGGER.error("Drink not found.");
            return new ResponseEntity<>(drink.get(), HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Drink with alcoholContent: " + alcoholContent + " was found successfully.");
        return new ResponseEntity<>(drink.get(), HttpStatus.OK);
    }

    public ResponseEntity<Drinks> deleteDrinkById(Long id) {
        Optional<Drinks> drink = drinksRepository.findById(id);

        if(drink.isEmpty()) {
            LOGGER.error("Drink not found.");
            return new ResponseEntity<>(drink.get(), HttpStatus.NOT_FOUND);
        }
        drinksRepository.deleteById(id);
        LOGGER.info("Drink was deleted successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
