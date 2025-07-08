package com.prasoon.ImageProcessingService.service;


import com.prasoon.ImageProcessingService.dto.SignInRequest;
import com.prasoon.ImageProcessingService.dto.SignInResponse;
import com.prasoon.ImageProcessingService.dto.SignUpRequest;
import com.prasoon.ImageProcessingService.dto.SignUpResponse;
import com.prasoon.ImageProcessingService.exception.ConfictException;
import com.prasoon.ImageProcessingService.exception.NotFoundException;
import com.prasoon.ImageProcessingService.exception.UnauthorizedException;
import com.prasoon.ImageProcessingService.model.Role;
import com.prasoon.ImageProcessingService.model.User;
import com.prasoon.ImageProcessingService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponse register(SignUpRequest request) {
        if(userRepository.existsById(request.email())){
            throw new ConfictException("User already exists with this email");
        }

        User user = User.builder()
                .firstName(request.firstName())
                .role(Role.USER)
                .email(request.email())
                .lastName(request.lastName())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
        return new SignUpResponse(request.firstName() + " " + request.lastName(),
                request.email(),
                jwtService.generatedIdToken(user),
                "User successfully registered");

    }

    public SignInResponse login(SignInRequest request) {

        if(!userRepository.existsById(request.email())){
            throw new NotFoundException("User Not found");
        }

        User user = userRepository.findById(request.email()).get();
        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new UnauthorizedException("Invalid Password");
        }

        return new SignInResponse(user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                jwtService.generatedIdToken(user),
                jwtService.generateAccessToken(user));
    }
}
