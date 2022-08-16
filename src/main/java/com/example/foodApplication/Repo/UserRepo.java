package com.example.foodApplication.Repo;

import com.example.foodApplication.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,String> {

    public User findByEmailAndPassword(String email,String password);
}
