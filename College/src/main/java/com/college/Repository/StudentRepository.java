package com.college.Repository;

import com.college.Entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
   @EntityGraph( value="studentEntityGraphs")
    List<StudentEntity> findAll();
}
