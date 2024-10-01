package com.college.Repository;

import com.college.Entity.HouseKeepingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseKeepingService {
    @Autowired
    private HouseKeepingRepository houseKeepingRepository;
    public HouseKeepingEntity register(HouseKeepingEntity houseKeepingEntity){
        return  houseKeepingRepository.save(houseKeepingEntity);
    }
    public List<HouseKeepingEntity> fetch(){
        return  houseKeepingRepository.findAll();
    }

}
