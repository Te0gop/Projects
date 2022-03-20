package com.spring.shopapp.service;
import com.spring.shopapp.entity.Foods;
import com.spring.shopapp.repository.FoodsRepository;
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
public class FoodService {

    private static Logger LOGGER = LoggerFactory.getLogger(DrinkService.class);

    @Autowired
    FoodsRepository foodRepository;

    public ResponseEntity<Foods> getFoodById(Long id) {
        LOGGER.warn("Searching food with id: " + id);

        Optional<Foods> food = foodRepository.findById(id);

        if(food.isEmpty()) {
            LOGGER.error("Food with id: " + id + " was not found.");
            return new ResponseEntity<>(food.get(), HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Food with id: " + id + " was found successfully.");
        return new ResponseEntity<>(food.get(), HttpStatus.OK);
    }

    public ResponseEntity<Foods> addFood(Foods food) {
        LOGGER.warn("Adding new food to repository: " + food.getName());
        foodRepository.save(food);
        LOGGER.info(food.getName() + " was saved successfully.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Foods> updateFood(Long id, Foods food) {
        LOGGER.warn("Updating food with id: " + id);
        Optional<Foods> existingFood = foodRepository.findById(id);

        if(existingFood.isEmpty()) {
            LOGGER.error("Food not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(food, existingFood.get(), "id");
        foodRepository.save(existingFood.get());
        LOGGER.info(food.getName() + " was updated successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Foods>> findFoodByName(String foodName) {
        LOGGER.warn("Searching food with name: " + foodName);
        List<Foods> food = foodRepository.findFoodByName(foodName);

        if(food.isEmpty()) {
            LOGGER.error("Food not found.");
            new ResponseEntity<>(food, HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Food with name: " + foodName + " was found successfully.");
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    public ResponseEntity<List<Foods>> findFoodByPrice(BigDecimal price) {
        LOGGER.warn("Searching food with price: " + price);
        List<Foods> food = foodRepository.findByPrice(price);

        if(food.isEmpty()) {
            LOGGER.error("Foods with price: "  + price + " was not found.");
            return new ResponseEntity<>(food, HttpStatus.NOT_FOUND);
        }
        LOGGER.info(food.size() + " foods with price: " + price + " was found.");
        return  new ResponseEntity<>(food, HttpStatus.OK);
    }

    public ResponseEntity<List<Foods>> findFoodByManufacturerName(String manufacturerName) {
        LOGGER.warn("Searching food with manufacturer name: " + manufacturerName);
        List<Foods> food = foodRepository.findByManufacturerName(manufacturerName);

        if(food.isEmpty()) {
            LOGGER.error("Foods with manufacturer: "  + manufacturerName + " was not found.");
            return new ResponseEntity<>(food, HttpStatus.NOT_FOUND);
        }
        LOGGER.info(food.size() + " foods with manufacturer: " + manufacturerName + " was found.");
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    public ResponseEntity<List<Foods>> findFoodByExpiryDate(String expiryDate) {
        LOGGER.warn("Searching food with expiry date: " + expiryDate);
        List<Foods> food = foodRepository.findByExpiryDate(expiryDate);

        if(food.isEmpty()) {
            LOGGER.error("Foods with expiry date: "  + expiryDate + " was not found.");
            return new ResponseEntity<>(food, HttpStatus.NOT_FOUND);
        }
        LOGGER.info(food.size() + " foods with expiry date: " + expiryDate + " was found.");
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
    public ResponseEntity<List<Foods>> findAllFoods() {
        LOGGER.warn("Searching all foods...");
        List<Foods> drinks = new ArrayList<>(foodRepository.findAll());
        LOGGER.info(drinks.size() + " foods was found.");
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    public ResponseEntity<Foods> deleteFoodById(Long id) {
        Optional<Foods> food = foodRepository.findById(id);

        if(food.isEmpty()) {
            LOGGER.error("Food with id: " + id + " was not found.");
            return new ResponseEntity<>(food.get(), HttpStatus.NOT_FOUND);
        }
        foodRepository.deleteById(id);
        LOGGER.info("Food with id: " + id + " was deleted successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
