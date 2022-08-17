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



    public  boolean addProducts(Product addedProduct,String token) {//
        try
        {
            if(jwtUtils.validateJwtToken(token))
            {
                productRepo.save(addedProduct);
                return true;
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
                System.out.println("sd");
                String email= jwtUtils.getUserNameFromJwtToken(token);
                String password = userServices.findByEmail(email).getPassword();
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password));
                String jwt = jwtUtils.generateJwtToken(authentication);
                if(!productRepo.existsById(id))
                    throw new InvalidProductIDException();
                productRepo.deleteById(id);
                return ResponseEntity.ok().header("Authorization", jwt).build();
            }
        }
        catch (Exception e){
            System.out.println(e);
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
                String password = userServices.findByEmail(email).getPassword();
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password));
                String jwt = jwtUtils.generateJwtToken(authentication);
                return ResponseEntity.ok().header("Authorization", jwt).body(productRepo.findByCategoryIgnoreCase(category));
            }
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }
        throw new InvalidTokenException();
    }
}
