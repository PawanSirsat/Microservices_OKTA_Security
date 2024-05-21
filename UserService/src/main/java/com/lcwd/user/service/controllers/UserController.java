package com.lcwd.user.service.controllers;


import com.lcwd.user.service.entities.User;

import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")    
public class UserController {
	
    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    //create
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    
    //all user get
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<User>> getUserList() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
    
    //user get by id
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) 
    {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);   
    }
    
    int retryCount = 1;
    //user get by id
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/Json/{userId}")
//  @CircuitBreaker(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
//  @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserByIdJson(@PathVariable String userId) 
    {
        logger.info("Retry Count : {}"+retryCount);
        retryCount++;
        User user = userService.getUserJson(userId);
        return ResponseEntity.ok(user);   
    }

    //Fallback method call when hotel or rating service down
    public ResponseEntity<User> ratingHotelFallback(@PathVariable String userId,Exception ex) 
    {
    	   ex.printStackTrace();
    	   logger.info("fallback is active : Service is down "+ex);
    	   User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
           return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }
}
