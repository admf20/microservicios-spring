package com.microservice.course.client;

import com.microservice.course.entities.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/*
Configuración para hacer la relación con microservicio para comunicarse
  - name: se saca del application.yml
  - url: ruta donde se comunica el servicio incluyendo la ruta inicial que tiene el @RequestMapping
*/
@FeignClient(name = "msvc-student", url = "localhost:8090/api/student")
public interface StudentClient {

    @GetMapping("/search-by-course/{courseId}")
    List<StudentDTO> findAllStudentByCourse(@PathVariable Long courseId);
}
