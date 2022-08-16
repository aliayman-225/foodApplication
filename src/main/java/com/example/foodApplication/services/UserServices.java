package com.example.foodApplication.services;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Entitydto.Userdto;
import com.example.foodApplication.Repo.UserRepo;
import com.example.foodApplication.exception.EmployeeNotFoundException;
import com.example.foodApplication.exception.ErrorResponse;
import com.example.foodApplication.exception.Errors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    static UserRepo userRepo;

    @Autowired
    public UserServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public static boolean createNewUser(User addedUser) {
        if(userRepo.findById(addedUser.getEmail()).isPresent())
        {
            userRepo.save(addedUser);
            return true;
        }
        return false;
    }


    public static ResponseEntity<?> findByEmailAndPassword(String email,String password)//work
    {
        User scopedUser=userRepo.findByEmailAndPassword(email,password);
        if(scopedUser==null)
            throw new EmployeeNotFoundException();
        Userdto scopedUserdto=new Userdto();
        BeanUtils.copyProperties(scopedUser,scopedUserdto);
        return new ResponseEntity<>(scopedUserdto, HttpStatus.OK);
    }

    public static boolean logIn(String email,String password) {
        Optional<User> user=userRepo.findById(email);
        if(!user.isPresent() &&password.equals(user.get().getPassword()))//
        {
            return true;
        }
        return false;
    }

}
