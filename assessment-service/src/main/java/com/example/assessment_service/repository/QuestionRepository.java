package com.example.assessment_service.repository;

import com.example.assessment_service.entity.Assessment;
import com.example.assessment_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByAssessment(Assessment assessment);
}
