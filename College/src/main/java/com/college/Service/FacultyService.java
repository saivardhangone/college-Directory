package com.college.Service;

import com.college.Entity.BranchEntity;
import com.college.Entity.EmployeeEntity;
import com.college.Entity.FacultyEntity;
import com.college.Repository.BranchRepository;
import com.college.Repository.FacultyRepository;
import com.college.dto.FacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private BranchRepository branchRepository;
    public FacultyEntity register(FacultyDto facultyDto){
        FacultyEntity facultyEntity=new FacultyEntity();
        EmployeeEntity employeeEntity=new EmployeeEntity();
        employeeEntity.setEmployeeId(facultyDto.getEmployeeId());
        facultyEntity.setEmployeeId(employeeEntity);
        facultyEntity.setFacultyId(facultyDto.getFacultyId());
        FacultyEntity response=facultyRepository.save(facultyEntity);
        return response;
    }
    public List<FacultyEntity> fetch(){

        return facultyRepository.findAll();
    }
    public String assigningBranchToFaculties(Integer facultyId,Integer branchId){
        FacultyEntity faculty=this.facultyRepository.getById(facultyId);
        Set<FacultyEntity> facultyEntitySet=new HashSet<>();
        BranchEntity branchEntity=branchRepository.getById(branchId);
        facultyEntitySet.add(faculty);
        branchEntity.setFaculties(facultyEntitySet);
        BranchEntity branch=branchRepository.save(branchEntity);
        return "saved";

    }
    public FacultyEntity getById(Integer id){
       Optional<FacultyEntity> faculty=facultyRepository.findById(id);
       return faculty.isPresent()?faculty.get():null;
    }
}
