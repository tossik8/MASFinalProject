package nikita.toropov.masfinalproject.repository;

import nikita.toropov.masfinalproject.model.person.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
