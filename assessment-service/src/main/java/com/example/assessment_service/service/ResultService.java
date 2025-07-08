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
import java.util.Map;
import java.util.Optional;

@Service
public class ResultService {

    private final StudentAnswerRepository answerRepo;
    private final AssessmentResultRepository resultRepo;
    private final QuestionRepository questionRepo;
    private final AssessmentRepository assessmentRepo;

    public ResultService(StudentAnswerRepository answerRepo, AssessmentResultRepository resultRepo, QuestionRepository questionRepo, AssessmentRepository assessmentRepo) {
        this.answerRepo = answerRepo;
        this.resultRepo = resultRepo;
        this.questionRepo = questionRepo;
        this.assessmentRepo = assessmentRepo;
    }

    public AssessmentResult submitAnswers(Long studentId, Long assessmentId, Map<Long, String> answers) {
        Assessment assessment = assessmentRepo.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        int correct = 0;
        for (Map.Entry<Long, String> entry : answers.entrySet()) {
            Long questionId = entry.getKey();
            String answer = entry.getValue();

            Question question = questionRepo.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setStudentId(studentId);
            studentAnswer.setQuestion(question);
            studentAnswer.setAnswer(answer);
            answerRepo.save(studentAnswer);

            if (question.getCorrectAnswer().equalsIgnoreCase(answer)) {
                correct++;
            }
        }

        int score = (int) ((correct * 100.0) / assessment.getQuestions().size());
        boolean passed = score >= 60;

        AssessmentResult result = new AssessmentResult();
        result.setStudentId(studentId);
        result.setAssessmentId(assessmentId);
        result.setScore(score);
        result.setPassed(passed);

        return resultRepo.save(result);
    }
}
