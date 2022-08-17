package com.example.foodApplication.controller;

import com.example.foodApplication.services.ProductServices;
import com.example.foodApplication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Home")
public class UserController {

    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/hi")
    public boolean deleteProduct()
    {
        return userServices.updateProfile("s","s","s");
    }

}
