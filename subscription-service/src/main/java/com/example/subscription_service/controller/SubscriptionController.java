package com.example.subscription_service.controller;

import com.example.subscription_service.entity.Subscription;
import com.example.subscription_service.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public Subscription create(@RequestBody Subscription sub) {
        return service.createSubscription(sub);
    }

    @GetMapping("/user/{userId}")
    public List<Subscription> getByUser(@PathVariable Long userId) {
        return service.getSubscriptionsByUser(userId);
    }

    @PutMapping("/{id}/status")
    public Subscription updateStatus(@PathVariable Long id, @RequestParam Subscription.Status status) {
        return service.updateSubscriptionStatus(id, status);
    }
}
