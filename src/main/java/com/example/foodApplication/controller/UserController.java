package com.example.foodApplication.controller;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Home")
public class UserController {

    @Autowired // btrbot bl @service el mawgooda f class EmployeeService
    private UserServices userServices;

    @PostMapping(value = "/Register")
    public boolean createNewUser(@RequestBody User addedUser)
    {
        return UserServices.createNewUser(addedUser);
    }


    @RequestMapping(value = "/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password)
    {
        return UserServices.findByEmailAndPassword(email,password);
    }

}
