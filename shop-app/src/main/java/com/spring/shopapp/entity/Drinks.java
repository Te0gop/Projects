package com.spring.shopapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="drinks", schema = "shop_project")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Drinks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    private BigDecimal price;
    private String manufacturerName;
    private double alcoholContent;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties("drinks")
    private Shop shop;

    public Drinks(Long id, String name, BigDecimal price, String manufacturerName, double alcoholContent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturerName = manufacturerName;
        this.alcoholContent = alcoholContent;
    }
    public Drinks() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
