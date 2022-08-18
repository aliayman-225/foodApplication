package com.example.foodApplication.services;

import com.example.foodApplication.Entity.User;
import com.example.foodApplication.Entitydto.CustomUser;
import com.example.foodApplication.Repo.UserRepo;
import com.example.foodApplication.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.foodApplication.JWT.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    public UserServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationServices authenticationServices;

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
     * @return the scoped user STATUS:200  or an error exception STATUS:400
     */
    public User findByEmail(String email) {
       return userRepo.findByEmail(email);
    }


    /**
     *
     * @param newEmail the updated email
     * @param newUsername the updated username
     * @param newPassword the updated password
     * @param token the token to check the validity
     * @return A start token and successful STATUS:200 or an error msg in the body STATUS:400
     */
    public ResponseEntity<?> updateProfile(String newEmail, String newUsername, String newPassword, String token)
    {
        if(!authenticationServices.emailValidate(newEmail))
            throw new InvalidEmailStructure();
        if(!authenticationServices.passwordValidate(newPassword))
            throw new InvalidPasswordStructure();

        if(userRepo.findByEmail(newEmail)==null || newEmail.equals(jwtUtils.getUserNameFromJwtToken(token)))
        {
            if(jwtUtils.validateJwtToken(token))
            {
                String oldemail= jwtUtils.getUserNameFromJwtToken(token);
                User user = userRepo.findByEmail(oldemail);
                User encryptedUser = new User(newEmail, newUsername,
                        encoder.encode(newPassword), null);
                userRepo.deleteById(user.getEmail());
                userRepo.save(encryptedUser);
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(newEmail, newPassword));
                String newJwt = jwtUtils.generateJwtToken(authentication);
                return ResponseEntity.ok().header("Authorization", newJwt).body("Successfully updated");
            }else
                throw new InvalidTokenException();
        }
        throw new TakenEmailException();
    }
}
