package com.example.subscription_service.service;

import com.example.subscription_service.entity.Subscription;
import com.example.subscription_service.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;
    private final RestTemplate restTemplate;

    public SubscriptionService(SubscriptionRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public Subscription createSubscription(Subscription sub) {
        // تحقق من وجود المستخدم
        String userUrl = "http://user-service/api/users/" + sub.getUserId();
        var userResponse = restTemplate.getForEntity(userUrl, String.class);
        if (!userResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("User not found with id " + sub.getUserId());
        }

        // تحقق من وجود الدورة
        String courseUrl = "http://course-service/api/courses/" + sub.getCourseId();
        var courseResponse = restTemplate.getForEntity(courseUrl, String.class);
        if (!courseResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Course not found with id " + sub.getCourseId());
        }

        return repository.save(sub);
    }

    public List<Subscription> getSubscriptionsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public Subscription updateSubscriptionStatus(Long subId, Subscription.Status status) {
        var sub = repository.findById(subId)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id " + subId));
        sub.setStatus(status);
        return repository.save(sub);
    }
}
