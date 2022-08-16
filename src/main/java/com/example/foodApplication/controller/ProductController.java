package com.example.foodApplication.controller;

import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Home")
public class ProductController {

    @Autowired // btrbot bl @service el mawgooda f class EmployeeService
    private ProductServices userServices;

    @PostMapping(value = "/addProducts")
    public boolean addProduct(@RequestBody Product addedProduct)
    {
        return ProductServices.addProducts(addedProduct);
    }

    @PostMapping(value = "/deleteProducts")
    public boolean deleteProduct(@RequestParam Long id)
    {
        return ProductServices.deleteProduct(id);
    }

    @RequestMapping(value = "/allProducts")
    public Iterable<Product> showAllProducts(@RequestParam String category,@RequestParam String token)
    {
        return ProductServices.showAllProducts(category,token);
    }




}
