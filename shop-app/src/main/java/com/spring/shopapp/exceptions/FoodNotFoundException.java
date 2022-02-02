package com.spring.shopapp.exceptions;

import com.spring.shopapp.entity.Foods;

public class FoodNotFoundException extends Throwable{
    public FoodNotFoundException(String message) {
        super(message);
    }
}
