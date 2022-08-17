package com.example.foodApplication.services;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Entitydto.CustomUser;
import com.example.foodApplication.Entitydto.Userdto;
import com.example.foodApplication.JWT.AuthTokenFilter;
import com.example.foodApplication.Repo.UserRepo;
import com.example.foodApplication.exception.TakenEmailException;
import com.example.foodApplication.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.foodApplication.JWT.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices implements UserDetailsService {

    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;
    /*@Autowired
    private AuthTokenFilter authTokenFilter;*/

    @Autowired
    public UserServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    /**
     * @param email takes user email for authentication check
     * @return The scoped user with his authorities if the credentials is true data
     * @throws UsernameNotFoundException if the credentials is false
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =userRepo.findByEmail(email);
        if(user==null)
            throw new UsernameNotFoundException("Incorrect email or password");
        return new CustomUser(user.getEmail(),user.getUsername(),user.getPassword(),true,true,true,true,mapToGrantedAuthorities());
    }


    /**
     * @return list of user authorities
     */
    private  static List<GrantedAuthority> mapToGrantedAuthorities(){
        List<GrantedAuthority> grantedAuthorityList=new ArrayList<>();
        return grantedAuthorityList;
    }
    /**
     * Find user from database by email
     * @param email the email of the scoped user
     * @return the scoped user or an error exception
     */
    public User findByEmail(String email) {
       return userRepo.findByEmail(email);
    }

    public boolean updateProfile(String email, String username, String password)
    {
        if(userRepo.findByEmail(email)==null)
        {

        }
        return true;
    }

}
