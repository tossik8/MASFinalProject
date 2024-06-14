package nikita.toropov.masfinalproject.dto;

import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.repository.BranchRepository;
import nikita.toropov.masfinalproject.service.BranchDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BranchDtoTest {


    private final BranchDtoMapper branchDtoMapper = new BranchDtoMapper();
    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void testBranchToDto(){
        Branch branch = branchRepository.findById(1000L).orElseThrow();
        BranchDto branchDto = branchDtoMapper.apply(branch);

        assertEquals(branch.getName(), branchDto.name());
        assertEquals(branch.getAddress(), branchDto.address());
    }
}
