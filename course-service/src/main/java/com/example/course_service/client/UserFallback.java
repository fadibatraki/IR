package com.example.course_service.client;

import org.springframework.stereotype.Component;

@Component
public class UserFallback implements UserClient {

    @Override
    public UserDTO getUserById(Long id) {
        UserDTO fallback = new UserDTO();
        fallback.setId(id);
        fallback.setName("Unknown");
        fallback.setEmail("unavailable@example.com");
        return fallback;
    }
}
