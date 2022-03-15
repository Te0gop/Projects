package com.spring.shopapp.controller;

import com.spring.shopapp.entity.Drinks;
import com.spring.shopapp.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DrinksController {

    @Autowired
    DrinkService drinkService;

    @RequestMapping(value = "/drinks/{id}", method = RequestMethod.GET)
    public ResponseEntity<Drinks> findDrinkById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(drinkService.getDrinkById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/drinks/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Drinks>> findDrinkByName(@PathVariable("name") String name) {
        return drinkService.getDrinkByName(name);
    }

    @RequestMapping(value = "/drinks", method = RequestMethod.GET)
    public ResponseEntity<List<Drinks>> findAllDrinks() {
        return drinkService.findAllDrinks();
    }

    @RequestMapping(value = "/drinks", method = RequestMethod.POST)
    public ResponseEntity<Drinks> addDrinks(@RequestBody Drinks drink) {
        return drinkService.addDrink(drink);
    }

    @RequestMapping(value = "/drinks", method = RequestMethod.PUT)
    public ResponseEntity<Drinks> updateDrinks(@RequestBody Drinks drink) {
        return drinkService.addDrink(drink);
    }

    @RequestMapping(value = "/drinks/price/{price}", method = RequestMethod.GET)
    public ResponseEntity<Drinks> findDrinkByPrice(@PathVariable("price") BigDecimal price) {
        return drinkService.findDrinkByPrice(price);
    }
    @RequestMapping(value = "/drinks/manufacturerName/{manufacturerName}", method = RequestMethod.GET)
    public ResponseEntity<List<Drinks>> findDrinkByManufacturerName(@PathVariable("manufacturerName") String manufacturerName) {
        return drinkService.findDrinkByManufacturerName(manufacturerName);
    }

    @RequestMapping(value = "/drinks/alcoholContent/{alcoholContent}", method = RequestMethod.GET)
    public ResponseEntity<Drinks> findDrinksByAlcoholContent(@PathVariable("alcoholContent") double alcoholContent) {
        return drinkService.findDrinksByAlcoholContent(alcoholContent);
    }


    @RequestMapping(value="/drinks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id ) {
        return drinkService.deleteDrinkById(id);
    }

}
