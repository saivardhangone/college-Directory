package com.college.Repository;

import com.college.Entity.AcademicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicRepository extends JpaRepository<AcademicEntity,Integer> {

}
