package nikita.toropov.masfinalproject.repository.account;

import nikita.toropov.masfinalproject.model.account.InvestmentAccount;
import org.springframework.data.repository.CrudRepository;

public interface InvestmentAccountRepository extends CrudRepository<InvestmentAccount, Long> {
}
