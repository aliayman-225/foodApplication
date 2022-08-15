package com.example.foodApplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data // getter+setter+NoargsConstructor
@AllArgsConstructor //constructor to all attributes
@ToString
@NoArgsConstructor
@Entity(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // byzeed lwahddo
    @Column(name = "id",nullable = false,insertable = false,updatable = false)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "price",nullable = false)
    private double price;
    @Column(name = "imageURL",nullable = false)
    private String image;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "isAddedToCart",nullable = false)
    private boolean isAddedToCart;
    @Column(name = "boughtItemsCount",nullable = false)
    private int boughtItemsCount;
    @Column(name = "category",nullable = false)
    private String category;








}
