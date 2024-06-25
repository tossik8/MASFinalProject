package nikita.toropov.masfinalproject.service;


import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.BranchDto;
import nikita.toropov.masfinalproject.dto.account.AccountDto;
import nikita.toropov.masfinalproject.dto.person.ClientDetailsDto;
import nikita.toropov.masfinalproject.dto.person.ClientDto;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import nikita.toropov.masfinalproject.service.mapper.AccountDtoMapper;
import nikita.toropov.masfinalproject.service.mapper.BranchDtoMapper;
import nikita.toropov.masfinalproject.service.mapper.ClientDtoMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientDtoMapper;
    private final AccountDtoMapper accountDtoMapper;
    private final BranchDtoMapper branchDtoMapper;

    /**
     * Retrieves all clients and maps them to a set of {@code ClientDto}.
     *
     * @return a set of {@code ClientDto} representing all clients.
     */
    public Set<ClientDto> getClients(){
        Set<ClientDto> clientDtos = new HashSet<>();
        Iterable<Client> clients = clientRepository.findAll();
        for(Client client : clients){
            ClientDto clientDto = clientDtoMapper.apply(client);
            clientDtos.add(clientDto);
        }
        return clientDtos;
    }

    /**
     * Retrieves detailed information about a client including their accounts and branch details.
     *
     * @param id the unique identifier of the client whose details are to be retrieved.
     * @return a {@code ClientDetailsDto} object containing detailed information about the client.
     * @throws java.util.NoSuchElementException if no client with the specified {@code id} exists in the repository.
     */
    public ClientDetailsDto getClientDetails(long id){
        Client client = clientRepository.findById(id).orElseThrow();
        List<AccountDto> accountDtos = client.getAccounts().stream()
                .map(accountDtoMapper)
                .toList();
        BranchDto branchDto = branchDtoMapper.apply(client.getRegisteredAt());
        return new ClientDetailsDto(client.getId(),
                client.getName(),
                client.getSurname(),
                client.getCredentials().getEmail(),
                branchDto,
                accountDtos);
    }
}
