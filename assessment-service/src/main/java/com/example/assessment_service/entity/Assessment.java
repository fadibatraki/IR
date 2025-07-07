package com.example.assessment_service.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "assessments")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long courseId; // دورة مرتبطة بالاختبار

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> questions;

    public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public Long getCourseId() { return courseId; }
public void setCourseId(Long courseId) { this.courseId = courseId; }

public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }

public List<Question> getQuestions() { return questions; }
public void setQuestions(List<Question> questions) { this.questions = questions; }

}
