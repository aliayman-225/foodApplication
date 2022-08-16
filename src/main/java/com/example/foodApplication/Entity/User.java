package com.example.foodApplication.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;


@Data // getter+setter+NoargsConstructor
@AllArgsConstructor //constructor to all attributes
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
