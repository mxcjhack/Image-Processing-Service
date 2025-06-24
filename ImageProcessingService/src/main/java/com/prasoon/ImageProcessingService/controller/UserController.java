package com.prasoon.ImageProcessingService.controller;


import com.prasoon.ImageProcessingService.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("sign-up")
    public ResponseEntity<SignUpResonse> signUp(@RequestBody SignUpRequest request){
        System.out.println("Hits Controller");
        SignUpResonse sign = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sign);
    }

    @PostMapping("sign-in")
    public ResponseEntity<SignInResonse> signIn(@RequestBody SignInRequest request){
        System.out.println("Hits Controller");
        SignInResonse sign = authenticationService.login(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sign);
    }
}
