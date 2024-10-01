package com.college.Service;

import com.college.Entity.AcademicEntity;
import com.college.Entity.BranchEntity;
import com.college.Entity.StudentEntity;
import com.college.Repository.StudentRepository;
import com.college.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity register(StudentDto studentDto){
        StudentEntity studentEntity1=new StudentEntity();
        BranchEntity branchEntity=new BranchEntity();
        AcademicEntity academicEntity=new AcademicEntity();
        branchEntity.setId(studentDto.getBranchId());
        studentEntity1.setBranch(branchEntity);

        studentEntity1.setFirstName(studentDto.getFirstName());
        studentEntity1.setFamilyName(studentDto.getFamilyName());
        studentEntity1.setAddress(studentDto.getAddress());
        studentEntity1.setDob(studentDto.getDob());
        studentEntity1.setGender(studentDto.getGender());
        studentEntity1.setMobileNumber(studentDto.getMobileNumber());
        studentEntity1.setEmail(studentDto.getEmail());
        StudentEntity studentDetails=studentRepository.save(studentEntity1);
        return studentDetails;
    }
    public List<StudentEntity> fetch(){
        return studentRepository.findAll();
    }
    public  StudentEntity getById(Integer id){
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        return (studentEntity.isPresent())?studentEntity.get():null;
    }
}
