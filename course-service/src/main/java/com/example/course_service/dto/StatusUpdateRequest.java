package com.example.course_service.dto;

import com.example.course_service.entity.Course;

public class StatusUpdateRequest {
    private Course.Status status;

    public Course.Status getStatus() {
        return status;
    }

    public void setStatus(Course.Status status) {
        this.status = status;
    }
}
