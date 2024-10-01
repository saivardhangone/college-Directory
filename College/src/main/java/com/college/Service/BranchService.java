package com.college.Service;

import com.college.Entity.BranchEntity;
import com.college.Repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;
    public BranchEntity register(BranchEntity branchEntity){

        return branchRepository.save(branchEntity);
    }
    public List<BranchEntity> fetch(){
        return  branchRepository.findAll();
    }
    public BranchEntity getById(Integer id){
        Optional<BranchEntity> branch= branchRepository.findById(id);
        return (branch.isPresent())?branch.get():null;
    }
//    public BranchEntity getbyName(String name){
//        return (BranchEntity) branchRepository.findByCourseName(name);
//    }

}
