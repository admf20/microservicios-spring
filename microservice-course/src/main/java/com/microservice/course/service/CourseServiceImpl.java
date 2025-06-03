package com.microservice.course.service;

import com.microservice.course.client.StudentClient;
import com.microservice.course.entities.Course;
import com.microservice.course.entities.dto.StudentDTO;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.repository.ICourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepository ICourseRepository;
    private final StudentClient studentClient;

    public CourseServiceImpl(ICourseRepository ICourseRepository, StudentClient studentClient){
        this.ICourseRepository = ICourseRepository;
        this.studentClient = studentClient;
    }

    @Override
    public List<Course> findAll() {
        return (List<Course>) ICourseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return ICourseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        ICourseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByCourse(Long idCourse) {

        //consultar curso
        Course course = ICourseRepository.findById(idCourse).orElse(new Course());
        System.out.println("Curso: ".concat(course.getTeacher()));

        /* - Acá nos comunicamos con los con el otro microservicio de estudiante
           - El metodo nos retorna una lista de estudiantes, como no tenemos un objeto
             para trabajar con esa lista, creamos un StudentDTO
        * */
        List<StudentDTO> studentDTOSList = studentClient.findAllStudentByCourse(idCourse);
        System.out.println("Lista de estudiantes: ".concat(studentDTOSList.toString()));

        StudentByCourseResponse studentByCourseResponse = new StudentByCourseResponse();
        studentByCourseResponse.setCourseName(course.getName());
        studentByCourseResponse.setTeacher(course.getTeacher());
        studentByCourseResponse.setStudentDTOList(studentDTOSList);

        return studentByCourseResponse;
    }
}
