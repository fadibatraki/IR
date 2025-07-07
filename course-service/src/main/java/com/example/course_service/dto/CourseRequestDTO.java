package com.example.course_service.dto;

public class CourseRequestDTO {

    private String title;
    private String description;
    private Long trainerId;

    // getters & setters

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getTrainerId() { return trainerId; }
    public void setTrainerId(Long trainerId) { this.trainerId = trainerId; }
}
