package com.nyit.carrental.gateway.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nyit.carrental.gateway.config.JwtTokenProvider;
import com.nyit.carrental.gateway.domain.AuthLogin;
import com.nyit.carrental.gateway.domain.User;
import com.nyit.carrental.gateway.feign.UserFeignClient;

@RestController
public class VehicleLoginController {
	
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    UserFeignClient userFeignClient;

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<Object,Object>> login(@RequestBody AuthLogin data) {
        try {
            String username = data.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            //RestTemplate restTemplate = new RestTemplate();
           // User user = restTemplate.getForObject("http://localhost:8003/user/"+data.getEmail(), User.class);
            User user = userFeignClient.findUserByEmail(data.getEmail());
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return new ResponseEntity<>(model,HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }
	
}
