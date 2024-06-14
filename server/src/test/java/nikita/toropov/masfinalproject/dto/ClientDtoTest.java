package nikita.toropov.masfinalproject.dto;


import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import nikita.toropov.masfinalproject.service.AccountDtoMapper;
import nikita.toropov.masfinalproject.service.BranchDtoMapper;
import nikita.toropov.masfinalproject.service.ClientDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ClientDtoTest {

    private final AccountDtoMapper accountDtoMapper = new AccountDtoMapper();
    private final BranchDtoMapper branchDtoMapper = new BranchDtoMapper();
    private final ClientDtoMapper clientDtoMapper = new ClientDtoMapper(accountDtoMapper, branchDtoMapper);
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testClientToDto(){
        Client client = clientRepository.findById(1000L).orElseThrow();
        ClientDto clientDto = clientDtoMapper.apply(client);

        assertEquals(client.getId(), clientDto.id());
        assertEquals(client.getName(), clientDto.name());
        assertEquals(client.getSurname(), clientDto.surname());
        assertEquals(client.getCredentials().getEmail(), clientDto.email());
        assertEquals(client.getAccounts().size(), clientDto.accounts().size());
        assertNotNull(clientDto.registeredAt());
    }
}
