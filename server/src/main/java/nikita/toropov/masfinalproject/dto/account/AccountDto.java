package nikita.toropov.masfinalproject.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nikita.toropov.masfinalproject.model.account.Account;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public abstract class AccountDto {
    private final long id;
    private final String accountNumber;
    private final float balance;
    private final LocalDate openingDate;
    private final Account.Status status;
}
