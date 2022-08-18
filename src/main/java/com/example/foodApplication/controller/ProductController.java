package com.example.foodApplication.controller;

import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Home")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping(value = "/addProducts")
    public ResponseEntity<?> addProduct(@RequestBody Product addedProduct,@RequestHeader String Authorization)
    {
        return productServices.addProducts(addedProduct,Authorization);
    }

    @DeleteMapping(value = "/deleteProducts")
    public ResponseEntity<?>  deleteProduct(@RequestParam Long id,@RequestHeader String Authorization)
    {
        return productServices.deleteProduct(id,Authorization);
    }

    @RequestMapping(value = "/allProducts")
    public ResponseEntity<?> showAllProducts(@RequestParam String category, @RequestHeader String Authorization)
    {
        return productServices.showAllProducts(category,Authorization);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/allFoodProducts")
    public ResponseEntity<?> showAllFoodProducts(@RequestHeader String Authorization)
    {
        return productServices.showAllFoodProducts(Authorization);
    }






}
