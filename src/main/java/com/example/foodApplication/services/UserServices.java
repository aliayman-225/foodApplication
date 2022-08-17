package com.example.foodApplication.services;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Entitydto.CustomUser;
import com.example.foodApplication.Entitydto.Userdto;
import com.example.foodApplication.Repo.UserRepo;
import com.example.foodApplication.exception.TakenEmailException;
import com.example.foodApplication.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;






import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Entitydto.CustomUser;
import com.example.foodApplication.Entitydto.Userdto;
import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.Repo.UserRepo;
import com.example.foodApplication.exception.TakenEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public UserServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;


    public  User createNewUser(User addedUser) {
        if(addedUser!=null)
        {
            addedUser.setPassword(bCryptPasswordEncoder.encode(addedUser.getPassword()));
            return userRepo.save(addedUser);
        }
        throw new TakenEmailException();
        //return false;
    }


    public  ResponseEntity<?> findByEmailAndPassword(String email,String password)//work
    {
        User scopedUser=userRepo.findByEmailAndPassword(email,password);
        if(scopedUser==null)
            throw new UserNotFoundException();
        Userdto scopedUserdto=new Userdto();
        BeanUtils.copyProperties(scopedUser,scopedUserdto);
        return new ResponseEntity<>(scopedUserdto, HttpStatus.OK);
    }

    public  ResponseEntity<?>  logIn(String email) {
        User scopedUser=userRepo.findByEmail(email);
        Userdto scopedUserdto=new Userdto();
        BeanUtils.copyProperties(scopedUser,scopedUserdto);
        return new ResponseEntity<>(scopedUserdto, HttpStatus.OK);
       /* Optional<User> user=userRepo.findById(email);
        if(!user.isPresent())//
        {
            return true;
        }
        return false;*/
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user =userRepo.findByEmail(userName);
        if(user==null)
            throw new UsernameNotFoundException("Incorrect email or password");
        logIn(userName);
        return new CustomUser(user.getEmail(),user.getUsername(),user.getPassword(),true,true,true,true,mapToGrantedAuthorities());
    }

    private  static List<GrantedAuthority> mapToGrantedAuthorities(){
        List<GrantedAuthority> grantedAuthorityList=new ArrayList<>();
        return grantedAuthorityList;
    }


    public User findByEmail(String email) {
       return userRepo.findByEmail(email);
    }


    /*public ResponseEntity<?> signIn(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        CustomUser userDetails = (CustomUser) authentication.getPrincipal();
        return ResponseEntity.ok(new Userdto(userDetails.getUserName(), userDetails.getUsername(), jwt));
    }*/





}
