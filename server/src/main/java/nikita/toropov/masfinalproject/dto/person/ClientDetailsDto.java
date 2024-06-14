package nikita.toropov.masfinalproject.dto.person;

import nikita.toropov.masfinalproject.dto.BranchDto;
import nikita.toropov.masfinalproject.dto.account.AccountDto;

import java.util.List;

public record ClientDetailsDto(long id, String name,
                               String surname, String email,
                               BranchDto registeredAt, List<AccountDto> accounts) {
}
