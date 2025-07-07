package com.example.assessment_service.controller;

import com.example.assessment_service.entity.Question;
import com.example.assessment_service.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/assessment/{assessmentId}")
    public ResponseEntity<Question> addQuestionToAssessment(
            @PathVariable Long assessmentId,
            @RequestBody Question questionRequest
    ) {
        Question createdQuestion = questionService.addQuestionToAssessment(assessmentId, questionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<Question>> getQuestionsByAssessment(
            @PathVariable Long assessmentId
    ) {
        List<Question> questions = questionService.getQuestionsByAssessmentId(assessmentId);
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable Long questionId
    ) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
