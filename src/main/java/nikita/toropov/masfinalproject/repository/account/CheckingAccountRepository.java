package nikita.toropov.masfinalproject.repository.account;

import nikita.toropov.masfinalproject.model.account.CheckingAccount;
import org.springframework.data.repository.CrudRepository;

public interface CheckingAccountRepository extends CrudRepository<CheckingAccount, Long> {
}
