package com.NeoPractice.controller;

import com.NeoPractice.entity.UserDto;
import com.NeoPractice.entity.User;
import com.NeoPractice.repository.UserRepository;
import com.NeoPractice.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class Controller {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registration")
    public User registerUser(@RequestBody @Valid  User user) throws MessagingException, UnsupportedEncodingException {
        User user1=userRepository.save(user);
        if(user1 != null)
        {
            userService.sendOTPEmail(user);
        }
        return user1;
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody UserDto userDto)
    {
       return userService.loadUsername(userDto.getUsername(),userDto.getPassword());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
