package com.college.Controller;

import com.college.Entity.BranchEntity;
import com.college.Service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;
    @PostMapping("/")
    public BranchEntity register(@RequestBody BranchEntity branchEntity){
        return branchService.register(branchEntity);
    }
    @GetMapping("/")
    public List<BranchEntity> fetch(){
        return branchService.fetch();
    }

}
