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


    /**
     * The pattern that must be traced while typing an email "nnnnnnnnnnn@nnnnn.nnn"
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /**
     * The pattern that must be traced while typing a password "Aaaaaaaa8"
     */
    private static final Pattern VALID_PASSWORD_ADDRESS_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");

    /**
     *
     * @param emailStr The requested email
     * @return if it traces the email pattern or not
     */
    public  boolean emailValidate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    /**
     *
     * @param password The requested password
     * @return if it traces the password pattern or not
     */
    public  boolean passwordValidate(final String password) {
        Matcher matcher = VALID_PASSWORD_ADDRESS_REGEX.matcher(password);
        return matcher.matches();
    }

    /**
     * after a lot of check , encrypt the user data and save it in the database
     * @param user the registerd user
     * @return a successful msg  STATUS:200 or an error msg in the body STATUS:400
     */
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


    /**
     * creating a start token and check if the requested data is valid or not
     * @param email user email
     * @param password user password
     * @return A start token and successful STATUS:200 or an error msg in the body STATUS:400
     */
    public ResponseEntity<?> signIn(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String startJwt = jwtUtils.generateJwtToken(authentication);
            CustomUser userDetails = (CustomUser) authentication.getPrincipal();
            return ResponseEntity.ok().header("Authorization", startJwt).body(new Userdto(userDetails.getUsername(),userDetails.getUserName(),startJwt));
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new UserNotFoundException();
        }
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


}

