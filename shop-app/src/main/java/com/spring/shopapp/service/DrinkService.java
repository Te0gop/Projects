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
            LOGGER.error("Drink with id: " + id + " was not found.");
            return new ResponseEntity<>(drink.get(), HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Drink with id: " + id + " was found successfully.");
        return new ResponseEntity<>(drink.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Drinks>> getDrinkByName(String name) {
        LOGGER.warn("Searching drink with name: " + name);

        List<Drinks> drinkName = drinksRepository.findDrinkByName(name);

        if(drinkName.isEmpty()) {
            LOGGER.error("Drink not found.");
            new ResponseEntity<>(drinkName, HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Drink with name: " + name + " was found successfully.");
        return new ResponseEntity<>(drinkName, HttpStatus.OK);
    }

    public ResponseEntity<List<Drinks>> findAllDrinks() {
        LOGGER.warn("Searching all drinks...");
        List<Drinks> drinks = new ArrayList<>(drinksRepository.findAll());
        LOGGER.warn(drinks.size() + " drinks was found.");
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    public ResponseEntity<Drinks> addDrink(Drinks drink) {
        LOGGER.warn("Adding new drink to repository: " + drink.getName());
        drinksRepository.save(drink);
        LOGGER.info(drink.getName() + " was saved successfully.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Drinks> updateDrink(Drinks drink, Long id) {
        LOGGER.warn("Updating drink with id: " + id);
        Optional<Drinks> existingDrink = drinksRepository.findById(id);

        if(existingDrink.isEmpty()) {
            LOGGER.error("Drink not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(drink, existingDrink.get(), "id");
        drinksRepository.save(existingDrink.get());
        LOGGER.info(drink.getName() + " was updated successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Drinks>> findDrinkByPrice(BigDecimal price) {
        LOGGER.warn("Searching drink with price: " + price);
        List<Drinks> drinkPrice = drinksRepository.findByPrice(price);

        if(drinkPrice.isEmpty()) {
            LOGGER.error("Drinks with price: "  + price + " was not found.");
            return new ResponseEntity<>(drinkPrice, HttpStatus.NOT_FOUND);
        }
        LOGGER.info(drinkPrice.size() + " drinks with price: " + price + " was found.");
        return  new ResponseEntity<>(drinkPrice, HttpStatus.OK);
    }

    public ResponseEntity<List<Drinks>> findDrinkByManufacturerName(String manufacturerName) {
        LOGGER.warn("Searching drink with manufacturer name: " + manufacturerName);
        List<Drinks> drink = drinksRepository.findByManufacturerName(manufacturerName);

        if(drink.isEmpty()) {
            LOGGER.error("Drinks with manufacturer: "  + manufacturerName + " was not found.");
            return new ResponseEntity<>(drink, HttpStatus.NOT_FOUND);
        }
        LOGGER.info(drink.size() + " drinks with manufacturer: " + manufacturerName + " was found.");
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    public ResponseEntity<List<Drinks>> findDrinksByAlcoholContent(double alcoholContent) {
        LOGGER.warn("Searching drink with alcohol content: " + alcoholContent);
        List<Drinks> drink = drinksRepository.findByAlcoholContent(alcoholContent);

        if(drink.isEmpty()) {
            LOGGER.error("Drinks with alcohol content: "  + alcoholContent+ " was not found.");
            return new ResponseEntity<>(drink, HttpStatus.NOT_FOUND);
        }
        LOGGER.info(drink.size() + " drinks with alcohol content: " + alcoholContent + " was found.");
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    public ResponseEntity<Drinks> deleteDrinkById(Long id) {
        Optional<Drinks> drink = drinksRepository.findById(id);

        if(drink.isEmpty()) {
            LOGGER.error("Drink with id: " + id + " was not found.");
            return new ResponseEntity<>(drink.get(), HttpStatus.NOT_FOUND);
        }
        drinksRepository.deleteById(id);
        LOGGER.info("Drink with id: " + id + " was deleted successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
