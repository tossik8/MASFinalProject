package nikita.toropov.masfinalproject.repository;

import nikita.toropov.masfinalproject.model.account.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
