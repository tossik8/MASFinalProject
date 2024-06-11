package nikita.toropov.masfinalproject.service;


import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Retrieves a list of all clients from the repository.
     *
     * @return An iterable containing all client objects retrieved from the repository.
     * @see #clientRepository
     */
    public Iterable<Client> getClients(){
        return clientRepository.findAll();
    }

    /**
     * Retrieves a specific client by its ID.
     *
     * @param id The unique identifier of the client to retrieve.
     * @return The client object with the matching ID, or throws an exception if not found.
     * @throws java.util.NoSuchElementException if a client with the provided ID cannot be found in the repository.
     */
    public Client getClient(long id){
        return clientRepository.findById(id).orElseThrow();
    }
}
