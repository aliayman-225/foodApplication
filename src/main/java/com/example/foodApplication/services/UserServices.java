package com.example.foodApplication.services;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(userRepo.findById(addedUser.getEmail()).isEmpty())
        {
            userRepo.save(addedUser);
            return true;
        }
        return false;
    }

    public static boolean logIn(String email,String password) {
        Optional<User> user=userRepo.findById(email);
        if(!user.isEmpty() &&password.equals(user.get().getPassword()))
        {
            return true;
        }
        return false;
    }




}
