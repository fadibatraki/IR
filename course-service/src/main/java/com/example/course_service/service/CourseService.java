package com.example.course_service.service;

import com.example.course_service.dto.CourseRequestDTO;
import com.example.course_service.entity.Course;
import com.example.course_service.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    public Course getCourseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));
    }

    public List<Course> getCoursesByTrainer(Long trainerId) {
        return repository.findByTrainerId(trainerId);
    }

    public Course createCourse(CourseRequestDTO dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setTrainerId(dto.getTrainerId());
        course.setStatus(Course.Status.PENDING);
        return repository.save(course);
    }

    public Course updateCourseStatus(Long courseId, Course.Status status) {
        Course course = getCourseById(courseId);
        course.setStatus(status);
        return repository.save(course);
    }

    public void deleteCourse(Long id) {
        repository.deleteById(id);
    }
}
