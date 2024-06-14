package nikita.toropov.masfinalproject.service;

import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.BranchDto;
import nikita.toropov.masfinalproject.dto.account.AccountDto;
import nikita.toropov.masfinalproject.dto.ClientDto;
import nikita.toropov.masfinalproject.model.person.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientDtoMapper implements Function<Client, ClientDto> {

    private final AccountDtoMapper accountDtoMapper;
    private final BranchDtoMapper branchDtoMapper;

    /**
     * Maps a {@code Client} entity to a {@code ClientDto}.
     *
     * @param client the {@code Client} entity to be mapped.
     * @return a {@code ClientDto} containing the mapped data.
     */
    @Override
    public ClientDto apply(Client client) {
        List<AccountDto> accountDtos = client.getAccounts().stream()
                .map(accountDtoMapper)
                .collect(Collectors.toList());
        BranchDto branchDto = branchDtoMapper.apply(client.getRegisteredAt());
        return new ClientDto(client.getId(),
                    client.getName(),
                    client.getSurname(),
                    client.getCredentials().getEmail(),
                    branchDto,
                    accountDtos);
    }
}
