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
     * Retrieves a set of client data transfer objects ({@code ClientDto}).
     *
     * @return a set of {@code ClientDto} objects containing client information.
     */
    @GetMapping
    public Set<ClientDto> getClients(){
        return clientService.getClients();
    }

    /**
     * Retrieves detailed information of a client identified by the given ID.
     *
     * @param id the unique identifier of the client.
     * @return a {@code ClientDetailsDto} object containing detailed information about the client.
     */
    @GetMapping("/{id}")
    public ClientDetailsDto getClientDetails(@PathVariable long id){
        return clientService.getClientDetails(id);
    }
}
