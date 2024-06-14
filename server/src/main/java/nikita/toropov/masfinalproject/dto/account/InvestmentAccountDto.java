package nikita.toropov.masfinalproject.dto.account;

import lombok.Getter;
import nikita.toropov.masfinalproject.model.account.Account;

import java.time.LocalDate;

@Getter
public class InvestmentAccountDto extends AccountDto{

    private final String investmentObjective;

    public InvestmentAccountDto(long id, String accountNumber, float balance, LocalDate openingDate, Account.Status status, String investmentObjective) {
        super(id, accountNumber, balance, openingDate, status);
        this.investmentObjective = investmentObjective;
    }
}
