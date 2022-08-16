package com.example.foodApplication.controller;
import com.example.foodApplication.Entity.User;
import com.example.foodApplication.services.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Home")
public class AuthenticationController {
    @Autowired
    AuthenticationServices authenticationServices;

    @PostMapping(value = "/Register")
    public ResponseEntity<?>  signup(@RequestBody User addedUser)
    {
        return authenticationServices.signUP(addedUser);
    }


   @RequestMapping(value = "/login")
    public ResponseEntity<?>  login(@RequestParam String email,@RequestParam String password)
    {
        return authenticationServices.signIn(email,password);
    }

}
