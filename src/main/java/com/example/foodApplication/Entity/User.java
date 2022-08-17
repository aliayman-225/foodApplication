package com.example.foodApplication.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity(name = "Customer")
public class User {


    @Id
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name="username",nullable = false)
    private String username;
    @Column(name="password",nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products = new java.util.ArrayList<>();




}
