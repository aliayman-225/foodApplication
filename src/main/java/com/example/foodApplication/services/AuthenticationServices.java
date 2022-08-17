package com.example.foodApplication.services;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Entitydto.CustomUser;
import com.example.foodApplication.Entitydto.Userdto;
import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.Repo.UserRepo;
import com.example.foodApplication.exception.InvalidEmailStructure;
import com.example.foodApplication.exception.InvalidPasswordStructure;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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



    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");
    public static boolean emailValidate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    public static boolean passwordValidate(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public ResponseEntity<?> signUP(User user) {
        if(!emailValidate(user.getEmail()))
            throw new InvalidEmailStructure();
        if(!passwordValidate(user.getPassword()))
            throw new InvalidPasswordStructure();


        if (userRepo.findByEmail(user.getEmail())!=null) {
            throw new TakenEmailException();
        }
        User encryptedUser = new User(user.getEmail(), user.getUsername(),
                encoder.encode(user.getPassword()), null);
        userRepo.save(encryptedUser);
        return ResponseEntity.ok(("User registered successfully"));
    }

    public boolean signup(User user) {
        if (userRepo.findByEmail(user.getEmail())!=null) {
            return false;
        }
        User encryptedUser = new User(user.getEmail(), user.getUsername(),
                encoder.encode(user.getPassword()), null);
        userRepo.save(encryptedUser);
        return true;
    }

        public ResponseEntity<?> signIn(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));


            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            CustomUser userDetails = (CustomUser) authentication.getPrincipal();
            return ResponseEntity.ok(new Userdto(userDetails.getUsername(),userDetails.getUserName(), jwt));

        }
        catch (Exception e)
            {
                throw new UserNotFoundException();
            }
    }
}

