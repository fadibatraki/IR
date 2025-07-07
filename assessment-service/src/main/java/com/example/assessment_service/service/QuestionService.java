package com.example.assessment_service.service;

import com.example.assessment_service.entity.Assessment;
import com.example.assessment_service.entity.Question;
import com.example.assessment_service.repository.AssessmentRepository;
import com.example.assessment_service.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AssessmentRepository assessmentRepository;

    public QuestionService(QuestionRepository questionRepository, AssessmentRepository assessmentRepository) {
        this.questionRepository = questionRepository;
        this.assessmentRepository = assessmentRepository;
    }

    public Question addQuestionToAssessment(Long assessmentId, Question questionRequest) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found with id " + assessmentId));

        Question question = new Question();
        question.setText(questionRequest.getText());
        question.setCorrectAnswer(questionRequest.getCorrectAnswer());
        question.setAssessment(assessment);

        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByAssessmentId(Long assessmentId) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found with id " + assessmentId));

        return questionRepository.findByAssessment(assessment);
    }

    public void deleteQuestion(Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new RuntimeException("Question not found with id " + questionId);
        }
        questionRepository.deleteById(questionId);
    }
}
