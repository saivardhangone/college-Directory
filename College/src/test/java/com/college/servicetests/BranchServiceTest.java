package com.college.servicetests;
import com.college.Entity.BranchEntity;
import com.college.Repository.BranchRepository;
import com.college.Service.BranchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BranchServiceTest {
    @Autowired
     BranchService branchService=new BranchService();

    BranchRepository branchRepository;

    @Before
    public void setUp() throws Exception {
        BranchEntity branch=new BranchEntity(6,"Ml");
        when(branchRepository.findByCourseName("ML")).thenReturn((List<BranchEntity>) branch);

    }



    @Test
    public void branchRegisterTest() {
        System.out.println("test 1");
        assertEquals("ML",branchService.getbyName("ML"));





    }
}
