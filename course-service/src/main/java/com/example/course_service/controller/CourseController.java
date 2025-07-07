package com.example.course_service.controller;

import com.example.course_service.dto.CourseRequestDTO;
import com.example.course_service.dto.CourseResponseDTO;
import com.example.course_service.dto.StatusUpdateRequest;
import com.example.course_service.entity.Course;
import com.example.course_service.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = service.getAllCourses().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        Course course = service.getCourseById(id);
        return ResponseEntity.ok(toResponseDTO(course));
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByTrainer(@PathVariable Long trainerId) {
        List<CourseResponseDTO> courses = service.getCoursesByTrainer(trainerId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        Course created = service.createCourse(courseRequestDTO);
        return ResponseEntity.ok(toResponseDTO(created));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CourseResponseDTO> updateStatus(@PathVariable Long id,
                                                          @RequestBody StatusUpdateRequest request) {
        Course updated = service.updateCourseStatus(id, request.getStatus());
        return ResponseEntity.ok(toResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // تحويل من entity إلى DTO
    private CourseResponseDTO toResponseDTO(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getTrainerId(),
                course.getStatus()
        );
    }
}
