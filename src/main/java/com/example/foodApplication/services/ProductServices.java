package com.example.foodApplication.services;


import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.Repo.ProductRepo;
import com.example.foodApplication.exception.InvalidProductIDException;
import com.example.foodApplication.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

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


    /**
     *
     * @param addedProduct the product that needed to be added to the database
     * @param token the token that checks if the current user has an access to add product
     * @return A new token with new session and a successful msg in the body
     */
    public  ResponseEntity<?> addProducts(Product addedProduct,String token) {//
        try {
            if (jwtUtils.validateJwtToken(token)) {
                String newJwt = jwtUtils.generateJwtTokenFromEmail(jwtUtils.getUserNameFromJwtToken(token));
                productRepo.save(addedProduct);
                return ResponseEntity.ok().header("Authorization", newJwt).body("Added successfully");
            }
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }

        throw new InvalidTokenException();
    }

    /**
     *
     * @param id the product id that needed to be deleted from the database
     * @param token the token that checks if the current user has an access to add product
     * @return A new token with new session and a successful msg in the body
     */
    public  ResponseEntity<?>  deleteProduct(Long id,String token) {
            try{
                if(jwtUtils.validateJwtToken(token))
                {
                    String newJwt = jwtUtils.generateJwtTokenFromEmail(jwtUtils.getUserNameFromJwtToken(token));
                    if(!productRepo.existsById(id))
                        throw new InvalidProductIDException();
                    productRepo.deleteById(id);
                    return ResponseEntity.ok().header("Authorization", newJwt).body("Deleted Successfully");
                }
            }
           catch (Exception e){
               throw new InvalidTokenException();
           }
        throw new InvalidTokenException();
    }


    /**
     *
     * @param category the food category that needed to be searched from the databas
     * @param token the token that checks if the current user has an access to add product
     * @return A new token with new session and a resulted products in the body
     */
    public  ResponseEntity<?> showAllProducts(String category,String token) {
        try
        {
            if(jwtUtils.validateJwtToken(token))
            {
                String newJwt = jwtUtils.generateJwtTokenFromEmail (jwtUtils.getUserNameFromJwtToken(token));
                return ResponseEntity.ok().header("Authorization", newJwt).body(productRepo.findByCategoryIgnoreCase(category));
            }
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }
        throw new InvalidTokenException();
    }


    /**
     *
     * To search for all the food in the database
     * @param token the token that checks if the current user has an access to add product
     * @return A new token with new session and a resulted products in the body
     */
    public  ResponseEntity<?> showAllFoodProducts(String token) {
       try
        {
            if(jwtUtils.validateJwtToken(token))
            {
                String newJwt = jwtUtils.generateJwtTokenFromEmail (jwtUtils.getUserNameFromJwtToken(token));

                return ResponseEntity.ok().header("Authorization", newJwt).body(productRepo.findAll());
            }
        }
        catch (Exception e){
            throw new InvalidTokenException();
        }
       throw new InvalidTokenException();
    }

}
