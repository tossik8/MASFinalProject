package nikita.toropov.masfinalproject.service;


import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.ClientDto;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientDtoMapper;

    public Set<ClientDto> getClients(){
        Set<ClientDto> clientDtos = new HashSet<>();
        Iterable<Client> clients = clientRepository.findAll();
        for(Client client : clients){
            ClientDto clientDto = clientDtoMapper.apply(client);
            clientDtos.add(clientDto);
        }
        return clientDtos;
    }
}
