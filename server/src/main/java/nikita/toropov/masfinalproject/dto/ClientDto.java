package nikita.toropov.masfinalproject.dto;

import nikita.toropov.masfinalproject.dto.account.AccountDto;

import java.util.List;

public record ClientDto(long id, String name,
                        String surname, String email,
                        BranchDto registeredAt, List<AccountDto> accounts) {
}
