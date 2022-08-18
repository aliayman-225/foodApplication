package com.example.foodApplication.services;


import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.Entitydto.Userdto;
import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.Repo.ProductRepo;
import com.example.foodApplication.exception.InvalidProductIDException;
import com.example.foodApplication.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    static ProductRepo productRepo;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public ProductServices(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Autowired
    UserServices userServices;



    public  ResponseEntity<?> addProducts(Product addedProduct,String token) {//
        try
        {
            if(jwtUtils.validateJwtToken(token))
            {
                String email= jwtUtils.getUserNameFromJwtToken(token);
                String jwt = jwtUtils.generateJwtTokenFromEmail(email);
                productRepo.save(addedProduct);
                return ResponseEntity.ok().header("Authorization", jwt).body("Added successfully");
            }
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }
        throw new InvalidTokenException();
    }
    public  ResponseEntity<?>  deleteProduct(Long id,String token) {//

        try
        {
            if(jwtUtils.validateJwtToken(token))
            {
                String email= jwtUtils.getUserNameFromJwtToken(token);
                String jwt = jwtUtils.generateJwtTokenFromEmail(email);
                if(!productRepo.existsById(id))
                    throw new InvalidProductIDException();
                productRepo.deleteById(id);
                return ResponseEntity.ok().header("Authorization", jwt).build();
            }
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }
        throw new InvalidTokenException();
    }


    public  ResponseEntity<?> showAllProducts(String category,String token) {
        try
        {
            if(jwtUtils.validateJwtToken(token))
            {
                String email=jwtUtils.getUserNameFromJwtToken(token);
                String jwt = jwtUtils.generateJwtTokenFromEmail (email);
                return ResponseEntity.ok().header("Authorization", jwt).body(productRepo.findByCategoryIgnoreCase(category));
            }
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }
        throw new InvalidTokenException();
    }


    public  ResponseEntity<?> showAllFoodProducts() {
      /*  try
        {
            if(jwtUtils.validateJwtToken(token))
            {
                String email=jwtUtils.getUserNameFromJwtToken(token);
                String jwt = jwtUtils.generateJwtTokenFromEmail (email);
                return ResponseEntity.ok().header("Authorization", jwt).body(productRepo.findAll());
            }

        }*/
        return ResponseEntity.ok().body("hii"+productRepo.findAll());

        /*catch (Exception e){
            throw new InvalidTokenException();
        }*/
      //  throw new InvalidTokenException();
    }



}
