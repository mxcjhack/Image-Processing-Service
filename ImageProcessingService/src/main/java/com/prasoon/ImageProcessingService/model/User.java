package com.prasoon.ImageProcessingService.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    private final String firstName;
    private final String lastName;
    @Id
    private final String email;
    private final String password;

    @Enumerated(EnumType.STRING)
    private final Role role;
}
