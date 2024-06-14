package nikita.toropov.masfinalproject.controller;

import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.ClientDto;
import nikita.toropov.masfinalproject.service.ClientService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@CrossOrigin
public class ClientController {

    private final ClientService clientService;

    /**
     * Retrieves a set of all clients.
     *
     * @return A set containing all client objects.
     * @see #clientService
     */
    @GetMapping
    public Set<ClientDto> getClients(){
        return clientService.getClients();
    }
}
