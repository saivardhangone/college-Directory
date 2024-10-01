package com.college.Controller;

import com.college.Entity.FacultyEntity;
import com.college.Exception.FacultyException;
import com.college.Exception.StudentNotFound;
import com.college.Service.FacultyService;
import com.college.dto.FacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college/faculty")

public class FacultyController {
    @Autowired
    private FacultyService facultyService;
    @PostMapping("/")
    public FacultyEntity register(@RequestBody FacultyDto faculty){
        return facultyService.register(faculty);
    }
    @GetMapping("/")
    public List<FacultyEntity> fetch(){
        return  facultyService.fetch();
    }
    @PostMapping("/branch/")
    public String assigningBranchToFaculties(@RequestParam("id") Integer facultyId,@RequestParam("branchId") Integer branchId){
        facultyService.assigningBranchToFaculties(facultyId,branchId);
        return "saved";
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyEntity> getbyId(@PathVariable("id") Integer id){
        FacultyEntity faculty=facultyService.getById(id);
        if(faculty==null){
            throw new FacultyException("this faculty is not found"+id);
        }
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }


}
