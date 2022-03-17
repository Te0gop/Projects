package com.spring.shopapp.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="foods", schema="shop_project")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Foods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    private String expiryDate;
    private String manufacturerName;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties("foods")
    private Shop shop;

    public Foods(Long id, String name, String expiryDate, String manufacturerName, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.manufacturerName = manufacturerName;
        this.price = price;
    }
    public Foods() {}

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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
