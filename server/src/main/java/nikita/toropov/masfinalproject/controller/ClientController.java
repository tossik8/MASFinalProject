package nikita.toropov.masfinalproject.controller;

import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.person.ClientDetailsDto;
import nikita.toropov.masfinalproject.dto.person.ClientDto;
import nikita.toropov.masfinalproject.service.ClientService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ClientDetailsDto getClientDetails(@PathVariable long id){
        return clientService.getClientDetails(id);
    }
}
