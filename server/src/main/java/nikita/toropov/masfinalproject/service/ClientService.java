package nikita.toropov.masfinalproject.service;


import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Iterable<Client> getClients(){
        return clientRepository.findAll();
    }
}
