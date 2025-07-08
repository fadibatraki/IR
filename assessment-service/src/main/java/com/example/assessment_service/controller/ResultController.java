package com.example.assessment_service.controller;

import com.example.assessment_service.entity.AssessmentResult;
import com.example.assessment_service.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // ✅ أضف هذا
import java.util.stream.Collectors; // ✅ نحتاجه لتجميع القائمة إلى خريطة

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    // DTO مخصص للإجابات
    public static class AnswerDto {
        private Long questionId;
        private String answer;

        public Long getQuestionId() { return questionId; }
        public void setQuestionId(Long questionId) { this.questionId = questionId; }
        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }
    }

    // جسم الطلب الكامل
    public static class SubmitRequest {
        private Long assessmentId;
        private Long studentId;
        private List<AnswerDto> answers;

        public Long getAssessmentId() { return assessmentId; }
        public void setAssessmentId(Long assessmentId) { this.assessmentId = assessmentId; }
        public Long getStudentId() { return studentId; }
        public void setStudentId(Long studentId) { this.studentId = studentId; }
        public List<AnswerDto> getAnswers() { return answers; }
        public void setAnswers(List<AnswerDto> answers) { this.answers = answers; }
    }

    @PostMapping("/submit")
    public ResponseEntity<AssessmentResult> submitAssessment(@RequestBody SubmitRequest request) {
        Map<Long, String> answersMap = request.getAnswers().stream()
            .collect(Collectors.toMap(AnswerDto::getQuestionId, AnswerDto::getAnswer));

        AssessmentResult result = resultService.submitAnswers(request.getStudentId(), request.getAssessmentId(), answersMap);
        return ResponseEntity.ok(result);
    }
}
