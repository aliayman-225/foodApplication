package com.example.foodApplication.controller;

import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Home")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping(value = "/addProducts")
    public boolean addProduct(@RequestBody Product addedProduct)
    {
        return ProductServices.addProducts(addedProduct);
    }

    @DeleteMapping(value = "/deleteProducts")
    public boolean deleteProduct(@RequestParam Long id)
    {
        return ProductServices.deleteProduct(id);
    }

    @RequestMapping(value = "/allProducts")
    public Iterable<Product> showAllProducts(@RequestParam String category,@RequestParam String token)
    {

        return productServices.showAllProducts(category,token);
    }




}
