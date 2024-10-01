package com.college.Controller;

import com.college.Entity.AcademicEntity;
import com.college.Service.AcademicService;
import com.college.dto.AcademicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college/academics")
public class AcademicController {
    @Autowired
    private AcademicService academicService;
    @PostMapping("/")
    public AcademicEntity register(@RequestBody AcademicDto academicDto){
        return academicService.register(academicDto);
    }
    @GetMapping("/")
    public List<AcademicEntity> fetch(){
        return academicService.fetch();
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        return academicService.delete(id);
    }
}
