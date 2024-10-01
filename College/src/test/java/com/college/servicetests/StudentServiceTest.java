package com.college.servicetests;

import com.college.Entity.BranchEntity;
import com.college.Entity.StudentEntity;
import com.college.Repository.StudentRepository;
import com.college.Service.StudentService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class StudentServiceTest {
@Autowired
    private StudentService studentService;
@MockBean
    private StudentRepository studentRepository;
@BeforeEach
//void setUp(){
//    Date myObj = new Date();
//    BranchEntity branch=new BranchEntity(12,"CSE");
//    Optional<StudentEntity> student=Optional.of(new StudentEntity(1,"sai","gone","male","9949951617","sai@gmail.com",null,"hyderabad",branch));
//    Mockito.when(studentRepository.findById(1)).thenReturn(student);
//}

@Test
public void testGetStudentById(){
    String name="sai";
    StudentEntity student=studentService.getById(1);
    assertEquals(name,student.getFirstName());
}

}
