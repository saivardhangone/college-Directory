package com.college.Service;

import com.college.Entity.AcademicEntity;
import com.college.Entity.StudentEntity;
import com.college.Repository.AcademicRepository;
import com.college.dto.AcademicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicService {
    @Value("${env.name}")
    private String env;
    public AcademicService(){
        System.out.println("the current env is : "+ env);
    }
    @Autowired
    private AcademicRepository academicRepository;
    public AcademicEntity register(AcademicDto academicDto){
        AcademicEntity academicEntity=new AcademicEntity();
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setId(academicDto.getStudentId());
        academicEntity.setStudent(studentEntity);
        academicEntity.setSemester(academicDto.getSemester());
        academicEntity.setAcademicYear(academicDto.getAcademicYear());
        AcademicEntity academic=academicRepository.save(academicEntity);

        return academic;
    }
    public List<AcademicEntity> fetch(){

        return academicRepository.findAll();
    }
    public  String  delete(Integer id){
    Optional<AcademicEntity> academic=academicRepository.findById(id);
    if(academic!=null) {
        academicRepository.deleteById(id);
    }
    else{
        return "data not found";
    }
        return "deleted";

    }
}
