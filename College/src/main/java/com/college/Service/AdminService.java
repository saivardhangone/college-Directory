package com.college.Service;

import com.college.Entity.AdminEntity;
import com.college.Entity.EmployeeEntity;
import com.college.Repository.AdminRepository;
import com.college.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public AdminEntity register(AdminDto adminDto){
        AdminEntity admin=new AdminEntity();
        EmployeeEntity emp=new EmployeeEntity();
        emp.setEmployeeId(adminDto.getEmployeeId());
        admin.setEmployeeId(emp);
        admin.setSection(adminDto.getSection());
        admin.setId(adminDto.getId());
        AdminEntity response=adminRepository.save(admin);
        return response;
    }
    public List<AdminEntity> fetch(){
        List<AdminEntity> results=adminRepository.findAll();
        return  results;
    }
}
