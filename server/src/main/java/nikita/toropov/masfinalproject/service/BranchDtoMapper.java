package nikita.toropov.masfinalproject.service;

import nikita.toropov.masfinalproject.dto.BranchDto;
import nikita.toropov.masfinalproject.model.Branch;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BranchDtoMapper implements Function<Branch, BranchDto> {

    /**
     * Maps a {@code Branch} entity to a {@code BranchDto}.
     *
     * @param branch the {@code Branch} entity to be mapped.
     * @return a {@code BranchDto} containing the mapped data.
     */
    @Override
    public BranchDto apply(Branch branch) {
        return new BranchDto(branch.getName(), branch.getAddress());
    }
}
