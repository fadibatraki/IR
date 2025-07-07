package com.example.assessment_service.controller;

import com.example.assessment_service.entity.Assessment;
import com.example.assessment_service.service.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService service;

    public AssessmentController(AssessmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Assessment> createAssessment(@RequestBody Assessment assessment) {
        Assessment created = service.createAssessment(assessment);
        return ResponseEntity.ok(created);
    }

    // إضافة: جلب كل التقييمات
    @GetMapping
    public ResponseEntity<List<Assessment>> getAllAssessments() {
        List<Assessment> list = service.getAllAssessments();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assessment>> getByCourse(@PathVariable Long courseId) {
        List<Assessment> list = service.getAssessmentsByCourse(courseId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assessment> getById(@PathVariable Long id) {
        Assessment assessment = service.getAssessmentById(id);
        return ResponseEntity.ok(assessment);
    }
}
