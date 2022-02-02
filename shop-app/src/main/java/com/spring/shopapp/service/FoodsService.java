package com.spring.shopapp.service;

import com.spring.shopapp.entity.Foods;
import com.spring.shopapp.exceptions.FoodNotFoundException;
import com.spring.shopapp.repository.FoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodsService {
    @Autowired
    private FoodsRepository foodsRepository;

    public List<Foods> listAll() {
        return (List<Foods>) foodsRepository.findAll();
    }

    public void save(Foods food) {
        foodsRepository.save(food);
    }

    public Foods get(Long id) throws FoodNotFoundException {
        Optional<Foods> result = foodsRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new FoodNotFoundException("Could not find any food with ID: " + id);
    }

    public void delete(Long id) throws FoodNotFoundException {
        Long count = foodsRepository.countById(id);
        if(count == null || count == 0) {
            throw new FoodNotFoundException("Could not find any food with ID: " + id);
        }
        foodsRepository.deleteById(id);
    }
}
