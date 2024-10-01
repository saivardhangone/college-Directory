package com.college.Service;

import com.college.Entity.EmployeeEntity;
import com.college.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity register(EmployeeEntity employeeEntity) {

        return employeeRepository.save(employeeEntity);
    }

    public List<EmployeeEntity> fetch() {
        try {
            List<EmployeeEntity> employees = employeeRepository.findAll();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
