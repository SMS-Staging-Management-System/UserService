package com.revature.services;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.revature.repos.UserRepo;

@Profile("test")
@Configuration
public class UserServiceTestConfiguration {
    @Bean
    @Primary
    public UserRepo userService() {
        return Mockito.mock(UserRepo.class);
    }
}