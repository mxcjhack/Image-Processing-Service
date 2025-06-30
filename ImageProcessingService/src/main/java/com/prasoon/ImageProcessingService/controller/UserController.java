package com.prasoon.ImageProcessingService.controller;


import com.prasoon.ImageProcessingService.dto.SignInRequest;
import com.prasoon.ImageProcessingService.dto.SignInResponse;
import com.prasoon.ImageProcessingService.dto.SignUpRequest;
import com.prasoon.ImageProcessingService.dto.SignUpResponse;
import com.prasoon.ImageProcessingService.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request){
        System.out.println("Hits Controller");
        SignUpResponse sign = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sign);
    }

    @PostMapping("login")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request){
        System.out.println("Hits Controller");
        SignInResponse sign = authenticationService.login(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sign);
    }
}
