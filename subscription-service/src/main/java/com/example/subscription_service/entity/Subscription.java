package com.example.subscription_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    public enum Status {
        REGISTERED, PASSED, FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long courseId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.REGISTERED;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
