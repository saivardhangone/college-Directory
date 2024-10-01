package com.college.Repository;

import com.college.Entity.HouseKeepingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseKeepingRepository extends JpaRepository<HouseKeepingEntity,Integer> {
}
