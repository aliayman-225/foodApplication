package com.example.foodApplication.controller;

import com.example.foodApplication.JWT.AuthTokenFilter;
import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.services.ProductServices;
import com.example.foodApplication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Home")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping (value = "/UpdateProfile")
    public ResponseEntity<?> updateProfile(@RequestParam String newEmail, @RequestParam String newUsername, @RequestParam String newPassword, @RequestHeader String Authorization)
    {
        return userServices.updateProfile(newEmail,newUsername,newPassword,Authorization);

    }

}
