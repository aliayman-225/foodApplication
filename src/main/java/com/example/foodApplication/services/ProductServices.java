package com.example.foodApplication.services;


import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    static ProductRepo productRepo;

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

    public static Iterable<Product> showAllProducts(String token,String category) {
         return productRepo.findAllByCategory(category);
    }
}
