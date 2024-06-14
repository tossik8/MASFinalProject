package nikita.toropov.masfinalproject.service;

import nikita.toropov.masfinalproject.dto.person.ClientDto;
import nikita.toropov.masfinalproject.model.person.Client;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClientDtoMapper implements Function<Client, ClientDto> {

    /**
     * Maps a {@code Client} entity to a {@code ClientDto}.
     *
     * @param client the {@code Client} entity to be mapped.
     * @return a {@code ClientDto} containing the mapped data.
     */
    @Override
    public ClientDto apply(Client client) {
        return new ClientDto(client.getId(),
                    client.getName(),
                    client.getSurname(),
                    client.getCredentials().getEmail());
    }
}
