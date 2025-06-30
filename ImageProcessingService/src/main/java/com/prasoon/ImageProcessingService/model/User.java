package com.prasoon.ImageProcessingService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User {

    private  String firstName;
    private  String lastName;
    @Id
    private  String email;
    private  String password;

    @Enumerated(EnumType.STRING)
    private  Role role;
}
