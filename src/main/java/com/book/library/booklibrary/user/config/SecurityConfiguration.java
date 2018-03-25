package com.book.library.booklibrary.user.config;

import com.book.library.booklibrary.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static String[] ROUTES = {"/**"};

    private final UserDetailsService userDetailsService;


    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(SecurityConfiguration.ROUTES).permitAll()
                .anyRequest()
                .authenticated()
        .and()
                .formLogin()
                .loginPage("/users/login")
                .passwordParameter("password")
                .usernameParameter("username")
        .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .permitAll()
        .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
        .and()
                .userDetailsService(this.userDetailsService)
                .csrf()
                .disable();

    }
}
