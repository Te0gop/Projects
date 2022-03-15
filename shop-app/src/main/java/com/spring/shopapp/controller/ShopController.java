package com.spring.shopapp.controller;

import com.spring.shopapp.entity.Shop;
import com.spring.shopapp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(name = "shop", method = RequestMethod.POST)
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        return shopService.createShop(shop);
    }

}
