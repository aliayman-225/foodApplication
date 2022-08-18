package com.example.foodApplication.controller;
import com.example.foodApplication.Entity.User;
import com.example.foodApplication.services.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping(value = "/register")
    public boolean  signup2(@RequestBody User addedUser)
    {
        return authenticationServices.signup(addedUser);
    }


   @GetMapping(value = "/login")
    public ResponseEntity<?>  login(@RequestParam String email, @RequestParam String password, HttpServletResponse response)
    {
        return authenticationServices.signIn(email,password);
    }

}
