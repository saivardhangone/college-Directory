package com.college.Controller;

import ch.qos.logback.core.model.Model;
import com.college.Entity.StudentEntity;
import com.college.Exception.StudentNotFound;
import com.college.Service.StudentService;
import com.college.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RestController
@RequestMapping("/college/student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/")
    public StudentEntity register(@RequestBody StudentDto studentDto) {
        return studentService.register(studentDto);
    }

    @GetMapping("/")
    public List<StudentEntity> fetch() {
        return studentService.fetch();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getById(@PathVariable("id") Integer id) throws Exception {
        StudentEntity student = studentService.getById(id);
        if (student == null) {
            throw new Exception("No data found"+id);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
