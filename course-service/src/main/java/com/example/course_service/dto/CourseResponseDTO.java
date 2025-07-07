package com.example.course_service.dto;

import com.example.course_service.entity.Course;

public class CourseResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Long trainerId;
    private Course.Status status;

    public CourseResponseDTO(Long id, String title, String description, Long trainerId, Course.Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.trainerId = trainerId;
        this.status = status;
    }

    // getters only

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Long getTrainerId() { return trainerId; }
    public Course.Status getStatus() { return status; }
}
