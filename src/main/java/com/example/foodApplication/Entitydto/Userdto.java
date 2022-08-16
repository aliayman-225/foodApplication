package com.example.foodApplication.Entitydto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // getter+setter+NoargsConstructor
@AllArgsConstructor //constructor to all attributes
@ToString
@NoArgsConstructor
public class Userdto {

    private String email,username;
}
