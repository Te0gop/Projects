package com.spring.shopapp.controller;

import com.spring.shopapp.entity.Drinks;
import com.spring.shopapp.repository.DrinksRepository;
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
public class DrinksController {

    @Autowired
    DrinksRepository drinksRepository;

    @RequestMapping(value = "drinks/{id}", method = RequestMethod.GET)
    public ResponseEntity<Drinks> findDrinkById(@PathVariable("id") Long id) {
        Optional<Drinks> drink = drinksRepository.findById(id);

        if(drink.isPresent()) {
            return new ResponseEntity<>(drink.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(drink.get(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "drinks", method = RequestMethod.POST)
    public ResponseEntity<Drinks> addDrinks(@RequestBody Drinks drink) {
        drinksRepository.save(drink);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "drinks", method = RequestMethod.PUT)
    public ResponseEntity<Drinks> updateDrinks(@RequestBody Drinks drink) {
        drinksRepository.save(drink);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "drinks/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Drinks>> findDrinkByName(@PathVariable("name") String name) {
        List<Drinks> drinkName = drinksRepository.findDrinkByName(name);
        return drinkName.isEmpty() ? new ResponseEntity<>(drinkName, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(drinkName, HttpStatus.OK);
    }

    @RequestMapping(value = "drinks/price/{price}", method = RequestMethod.GET)
    public ResponseEntity<Drinks> findDrinkByPrice(@PathVariable("price") BigDecimal price) {
        Optional<Drinks> drinkPrice = drinksRepository.findByPrice(price);

        return drinkPrice.isPresent() ? new ResponseEntity<>(drinkPrice.get(), HttpStatus.OK) :
                new ResponseEntity<>(drinkPrice.get(), HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "drinks/manufacturerName/{manufacturerName}", method = RequestMethod.GET)
    public ResponseEntity<List<Drinks>> findDrinkByManufacturerName(@PathVariable("manufacturerName") String manufacturerName) {
        List<Drinks> drink = drinksRepository.findByManufacturerName(manufacturerName);

            return drink.isEmpty() ? new ResponseEntity<>(drink, HttpStatus.NOT_FOUND) :
                    new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @RequestMapping(value = "drinks/alcoholContent/{alcoholContent}", method = RequestMethod.GET)
    public ResponseEntity<Drinks> findDrinksByAlcoholContent(@PathVariable("alcoholContent") double alcoholContent) {
        Optional<Drinks> drink = drinksRepository.findByAlcoholContent(alcoholContent);

        return drink.isPresent() ? new ResponseEntity<>(drink.get(), HttpStatus.OK) :
                new ResponseEntity<>(drink.get(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "drinks", method = RequestMethod.GET)
    public ResponseEntity<List<Drinks>> findAllDrinks() {
        List<Drinks> drinks = new ArrayList<>();
        drinksRepository.findAll().forEach(x -> drinks.add(x));

        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }
    @RequestMapping(value="drinks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id ) {
        drinksRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
