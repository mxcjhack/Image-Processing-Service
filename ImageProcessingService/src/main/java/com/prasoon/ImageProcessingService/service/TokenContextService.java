package com.prasoon.ImageProcessingService.service;

import com.prasoon.ImageProcessingService.exception.BadRequestException;
import com.prasoon.ImageProcessingService.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenContextService {

    private final JwtService jwtService;
    private final HttpServletRequest request;

    public String getEmailFromToken() {
        try{
            String token = extractToken();
            return jwtService.extractUserEmail(token);
        } catch (Exception ex){
            ex.printStackTrace();
            throw new UnauthorizedException(ex.getMessage());
        }
    }

    public String getRoleFromToken() {
        try{
            String token = extractToken();
            return jwtService.extractUserRole(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnauthorizedException(e.getMessage());
        }
    }

    private String extractToken() {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new BadRequestException("Invalid or missing Authorization header");
    }
}
