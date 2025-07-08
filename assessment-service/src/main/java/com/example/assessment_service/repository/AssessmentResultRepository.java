package com.example.assessment_service.repository;

import com.example.assessment_service.entity.Assessment;
import com.example.assessment_service.entity.AssessmentResult;
import com.example.assessment_service.entity.Question;
import com.example.assessment_service.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    Optional<AssessmentResult> findByStudentIdAndAssessmentId(Long studentId, Long assessmentId);
}
