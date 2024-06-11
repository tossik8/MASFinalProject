package nikita.toropov.masfinalproject.controller;

import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.service.ClientService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@CrossOrigin
public class ClientController {

    private final ClientService clientService;

    /**
     * Retrieves a list of all clients.
     *
     * @return An iterable containing all client objects.
     * @see #clientService
     */
    @GetMapping
    public Iterable<Client> getClients(){
        return clientService.getClients();
    }
}
