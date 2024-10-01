package com.college.Repository;

import com.college.Entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@EnableJpaRepositories

public interface BranchRepository extends JpaRepository<BranchEntity,Integer> {
    @Override
    Optional <BranchEntity> findById(Integer id);

//    List<BranchEntity> findByCourseName(String ml);
//    @Query("select * from branches where courseName=?")
//    List<BranchEntity> findByCourseName(String ml);
}
