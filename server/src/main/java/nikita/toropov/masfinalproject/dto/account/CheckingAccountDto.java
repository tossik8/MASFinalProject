package nikita.toropov.masfinalproject.dto.account;

import lombok.Getter;
import nikita.toropov.masfinalproject.model.account.Account;

import java.time.LocalDate;

@Getter
public class CheckingAccountDto extends AccountDto{

    private final int overdraftLimit;
    public CheckingAccountDto(long id, String accountNumber, float balance, LocalDate openingDate,
                              Account.Status status, int overdraftLimit) {
        super(id, accountNumber, balance, openingDate, status);
        this.overdraftLimit = overdraftLimit;
    }
}
