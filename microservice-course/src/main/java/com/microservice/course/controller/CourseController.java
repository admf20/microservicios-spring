package com.microservice.course.controller;

import com.microservice.course.entities.Course;
import com.microservice.course.service.CourseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseServiceImpl studentService;

    public CourseController(CourseServiceImpl studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void savedStudent(@RequestBody Course course){
        studentService.save(course);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findById(){
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.findById(id));
    }

    @GetMapping("/search-student/{idCourse}")
    public ResponseEntity<?> findStudetsByIdCourse(@PathVariable Long idCourse){
        return ResponseEntity.ok(studentService.findStudentsByCourse(idCourse));
    }
}
