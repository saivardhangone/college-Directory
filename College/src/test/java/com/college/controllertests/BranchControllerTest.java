package com.college.controllertests;

import com.college.Controller.BranchController;
import com.college.Entity.BranchEntity;
import com.college.Service.BranchService;
import com.college.Service.StudentService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BranchController.class)
public class BranchControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BranchService branchService;
    private BranchEntity branch;
    @BeforeEach
    void setUP(){
        branch=new BranchEntity(12,"MCA");

    }
    @Test
    public void registerbranchTest() throws  Exception{
        Mockito.when(branchService.register(branch)).thenReturn(branch);
        mockMvc.perform(MockMvcRequestBuilders.post("/college/branch").contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n"+"\"id\":12,\r\n"+"\name\":\"MCA\",\r\n"+"}")).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
