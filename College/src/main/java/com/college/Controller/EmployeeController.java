package com.college.Controller;

import com.college.Entity.EmployeeEntity;
import com.college.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/")
    public EmployeeEntity register(@RequestBody EmployeeEntity employeeEntity){
        return employeeService.register(employeeEntity);
    }
    @GetMapping("/")
    public List<EmployeeEntity> fetch(){

        return employeeService.fetch();
    }
}
