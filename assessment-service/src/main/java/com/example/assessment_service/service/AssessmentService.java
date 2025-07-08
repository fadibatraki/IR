package com.example.assessment_service.service;

import com.example.assessment_service.entity.Assessment;
import com.example.assessment_service.entity.AssessmentResult;
import com.example.assessment_service.entity.Question;
import com.example.assessment_service.entity.StudentAnswer;
import com.example.assessment_service.repository.AssessmentRepository;
import com.example.assessment_service.repository.QuestionRepository;
import com.example.assessment_service.repository.AssessmentResultRepository;
import com.example.assessment_service.repository.StudentAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentService {

    private final AssessmentRepository repository;

    public AssessmentService(AssessmentRepository repository) {
        this.repository = repository;
    }

    public Assessment createAssessment(Assessment assessment) {
        return repository.save(assessment);
    }

    public List<Assessment> getAssessmentsByCourse(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    public Assessment getAssessmentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Assessment not found"));
    }
    public List<Assessment> getAllAssessments() {
        return repository.findAll();
    }
}
