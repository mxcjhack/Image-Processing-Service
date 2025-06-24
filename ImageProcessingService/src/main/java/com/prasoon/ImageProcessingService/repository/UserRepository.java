package com.prasoon.ImageProcessingService.repository;

import com.prasoon.ImageProcessingService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
