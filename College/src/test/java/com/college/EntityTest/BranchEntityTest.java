package com.college.EntityTest;
import com.college.Entity.BranchEntity;
import com.college.Repository.BranchRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BranchEntityTest {
    @Autowired
    private BranchRepository branchRepository;

    private BranchEntity branchEntity;
//    @Autowired
//    private TestEntityManager entityManager;
    @BeforeEach
    void setUp(){
        BranchEntity branchEntity=new BranchEntity(6,"ML");
        branchRepository.save(branchEntity);


    }
    @AfterEach
    void tearDown(){
        branchEntity=null;
        branchRepository.deleteAll();

    }
    @Test
    public void branchEntityTest() throws Exception {

        List<BranchEntity> branchEntityList=branchRepository.findByCourseName("Ml");
        assertThat(branchEntityList.get(0).getBranchName()).isEqualTo("ML");
    }
}
