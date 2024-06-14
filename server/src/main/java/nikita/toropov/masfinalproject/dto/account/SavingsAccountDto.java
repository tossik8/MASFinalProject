package nikita.toropov.masfinalproject.dto.account;

import lombok.Getter;
import nikita.toropov.masfinalproject.model.account.Account;

import java.time.LocalDate;

@Getter
public class SavingsAccountDto extends AccountDto{

    private final float interestRate;

    public SavingsAccountDto(long id, String accountNumber, float balance, LocalDate openingDate, Account.Status status, float interestRate) {
        super(id, accountNumber, balance, openingDate, status);
        this.interestRate = interestRate;
    }
}
