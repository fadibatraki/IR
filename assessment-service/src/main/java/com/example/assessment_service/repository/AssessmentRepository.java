package com.example.assessment_service.repository;

import com.example.assessment_service.entity.Assessment;
import com.example.assessment_service.entity.AssessmentResult;
import com.example.assessment_service.entity.Question;
import com.example.assessment_service.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> findByCourseId(Long courseId);
}
