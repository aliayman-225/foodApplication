package com.example.foodApplication.Configuration;
import com.example.foodApplication.JWT.AuthEntryPointJWT;
import com.example.foodApplication.JWT.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.foodApplication.services.UserServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@ComponentScan("com.example.foodApplication.*")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
/**
 * This class responsible for security configuration (Authentication & Authorization)
 */
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Autowired
    private AuthEntryPointJWT unauthorizedHandler;

    /**
     * take an instance from UserServices to access the utilities that the user can do
     */
    @Autowired
    private UserServices userService;

    /**
     * take an instance from BCryptPasswordEncoder to access the utilities that the user can do
     */
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    /**
     * A List of paths that does not need authentication
     */
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/Home/register",
            "/Home/Register",
            "/Home/login",
            "/Home/all"
            // other public endpoints of your API may be appended to this array
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configuring Authentication as here the authentication is disabled from cookies
     * Configuring Authentication as here the authentication is enabled from the AUTH_WHITELIST to all users
     * Configuring Authentication as here the authentication is disabled for the rest of apis using Basic Security authorization
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated().and().httpBasic();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bcryptPasswordEncoder);

    }

}
