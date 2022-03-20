package com.spring.shopapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="shop", schema="shop_project")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String shopName;

    @OneToMany
    private List<Foods> foods;

    @OneToMany
    private List<Drinks> drinks;

    public Shop(Long id, String shopName, List<Foods> foods, List<Drinks> drinks) {
        this.id = id;
        this.shopName = shopName;
        this.foods = foods;
        this.drinks = drinks;
    }

    private Shop() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<Foods> getFoods() {
        return foods;
    }

    @JsonIgnore
    public void setFoods(List<Foods> foods) {
        this.foods = foods;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    @JsonIgnore
    public void setDrinks(List<Drinks> drinks) {
        this.drinks = drinks;
    }
}