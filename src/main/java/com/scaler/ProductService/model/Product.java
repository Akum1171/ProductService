package com.scaler.ProductService.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String image;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Category category;
    private int qty;
    private int numberOfOrders;
}
/*
   as per the business , depends on use cases
   1            --->            1
   product --------------->  category
   M            <------         1
==============================================
   EX2
   MOVIE ---------------------> Actor
   1            ---->           M
   M             -----           1
   SO MANY TO MANY HERE
 */
