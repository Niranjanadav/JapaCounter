package com.niranjan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niranjan.DTOs.JwtRequest;
import com.niranjan.DTOs.JwtResponse;
import com.niranjan.DTOs.RefreshTokenRequest;
import com.niranjan.entities.RefreshToken;
import com.niranjan.entities.User;
import com.niranjan.security.JwtHelper;
import com.niranjan.service.RefreshTokenService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        
        RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(userDetails.getUsername());

        JwtResponse response = new JwtResponse();

        response.setJwtToken(token);
        response.setRefreshToken(refreshToken.getRefresToken());
        response.setUsername(userDetails.getUsername());
        response.setId(refreshToken.getUser().getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/refresh")
    public JwtResponse refreshJwtToken(@RequestBody RefreshTokenRequest request){
    	  RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
          User user = refreshToken.getUser();
    	  String token = helper.generateToken(user);
    	  
    
    	  JwtResponse response = new JwtResponse();

    	  response.setJwtToken(token);
    	  response.setRefreshToken(refreshToken.getRefresToken());
    	  response.setUsername(user.getUsername());

    	  return response;

    }
    

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
