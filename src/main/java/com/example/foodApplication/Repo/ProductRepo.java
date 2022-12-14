package com.example.foodApplication.Repo;

import com.example.foodApplication.Entity.Product;
import com.example.foodApplication.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product,Long> {

    public List<Product> findByCategoryIgnoreCase(String category);
}
