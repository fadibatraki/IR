package com.example.course_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// اسم التطبيق يجب أن يطابق spring.application.name في user-service
@FeignClient(name = "USER-SERVICE", fallback = UserFallback.class)
public interface UserClient {
    @GetMapping("/api/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
