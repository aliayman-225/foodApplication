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
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name="username",length = 50,nullable = false)
    private String username;
    @Column(name="password",length = 50,nullable = false)
    private String password;

}
