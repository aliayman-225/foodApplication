package com.example.foodApplication.controller;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Entitydto.Userdto;
import com.example.foodApplication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Home")
public class UserController {

    @Autowired // btrbot bl @service el mawgooda f class EmployeeService
    private UserServices userServices;

}
