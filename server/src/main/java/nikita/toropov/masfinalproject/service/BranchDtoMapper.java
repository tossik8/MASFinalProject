package nikita.toropov.masfinalproject.service;

import nikita.toropov.masfinalproject.dto.BranchDto;
import nikita.toropov.masfinalproject.model.Branch;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BranchDtoMapper implements Function<Branch, BranchDto> {

    @Override
    public BranchDto apply(Branch branch) {
        return new BranchDto(branch.getName(), branch.getAddress());
    }
}
