package com.learning.webservices.restful_web_services.user.repository;

import com.learning.webservices.restful_web_services.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
