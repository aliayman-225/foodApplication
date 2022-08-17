package com.example.foodApplication.services;


import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.Repo.ProductRepo;
import com.example.foodApplication.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    static ProductRepo productRepo;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    public ProductServices(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }




    public  boolean addProducts(Product addedProduct,String token) {
        try
        {
            if(jwtUtils.validateJwtToken(token))
                productRepo.save(addedProduct);
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }
        throw new InvalidTokenException();
    }
    public static boolean deleteProduct(Long id) {
        productRepo.deleteById(id);
            return true;
    }

    public  List<Product> showAllProducts(String category,String token) {
        try
        {
            if(jwtUtils.validateJwtToken(token))
                return productRepo.findByCategoryIgnoreCase(category);
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }
        throw new InvalidTokenException();
    }
}
