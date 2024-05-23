package nikita.toropov.masfinalproject.repository.account;

import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountRepository extends CrudRepository<SavingsAccount, Long> {
}
