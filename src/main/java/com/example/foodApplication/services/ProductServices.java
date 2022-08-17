package com.example.foodApplication.services;


import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.Repo.ProductRepo;
import com.example.foodApplication.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    static ProductRepo productRepo;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    public ProductServices(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }




    public static boolean addProducts(Product addedProduct) {
            productRepo.save(addedProduct);
            return true;
    }
    public static boolean deleteProduct(Long id) {
       // Optional<Product> product=productRepo.findById(id);
        productRepo.deleteById(id);
        //if(!product.isEmpty())
        //{
          //  productRepo.delete(product.get());
            return true;
        //}
        //return false;
    }

    public  Iterable<Product> showAllProducts(String category,String token) {
        if(jwtUtils.validateJwtToken(token))
            return productRepo.findAllByCategory(category);
        throw new InvalidTokenException();
    }
}
