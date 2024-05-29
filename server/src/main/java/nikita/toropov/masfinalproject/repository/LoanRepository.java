package nikita.toropov.masfinalproject.repository;

import nikita.toropov.masfinalproject.model.loan.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long> {
}
