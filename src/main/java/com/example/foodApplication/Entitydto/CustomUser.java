package com.example.foodApplication.Entitydto;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;


public class CustomUser extends User {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;
    public CustomUser(String email, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection <GrantedAuthority> authorities){
        super(email,password,enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
        this.userName=username;

    }
}
