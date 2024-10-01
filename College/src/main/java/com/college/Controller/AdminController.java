package com.college.Controller;

import com.college.Entity.AdminEntity;
import com.college.Service.AdminService;
import com.college.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/")
    public AdminEntity register(@RequestBody AdminDto adminDto){
        return adminService.register(adminDto);
    }
    @GetMapping("/")
    public List<AdminEntity> fetch(){
        return adminService.fetch();
    }

}
