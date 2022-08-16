package com.example.foodApplication.services;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Entitydto.CustomUser;
import com.example.foodApplication.Entitydto.Userdto;
import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.Repo.UserRepo;
import com.example.foodApplication.exception.TakenEmailException;
import com.example.foodApplication.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {

    private UserRepo userRepo;

    @Autowired
    public AuthenticationServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;


    public ResponseEntity<?> signUP(User user) {
        if (userRepo.findByEmail(user.getEmail())!=null) {
            throw new TakenEmailException();
        }
        User encryptedUser = new User(user.getEmail(), user.getUsername(),
                encoder.encode(user.getPassword()), null);
        userRepo.save(encryptedUser);
        return ResponseEntity.ok(("User registered successfully"));
    }

        public ResponseEntity<?> signIn(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));


            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            CustomUser userDetails = (CustomUser) authentication.getPrincipal();
            return ResponseEntity.ok(new Userdto(userDetails.getUserName(), userDetails.getUsername(), jwt));

        }
        catch (Exception e)
            {
                throw new UserNotFoundException();
            }
    }
}

