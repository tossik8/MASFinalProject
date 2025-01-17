package nikita.toropov.masfinalproject.service.mapper;

import nikita.toropov.masfinalproject.dto.account.AccountDto;
import nikita.toropov.masfinalproject.dto.account.CheckingAccountDto;
import nikita.toropov.masfinalproject.dto.account.InvestmentAccountDto;
import nikita.toropov.masfinalproject.dto.account.SavingsAccountDto;
import nikita.toropov.masfinalproject.model.account.Account;
import nikita.toropov.masfinalproject.model.account.CheckingAccount;
import nikita.toropov.masfinalproject.model.account.InvestmentAccount;
import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccountDtoMapper implements Function<Account, AccountDto> {

    /**
     * Maps an {@code Account} entity to its corresponding {@code AccountDto} subtype.
     *
     * @param account the {@code Account} entity to be mapped.
     * @return an {@code AccountDto} representing the mapped data, or {@code null} if the account type is not recognized.
     */
    @Override
    public AccountDto apply(Account account) {
        if(account instanceof CheckingAccount){
            return new CheckingAccountDto(account.getId(),
                    account.getAccountNumber(),
                    account.getBalance(),
                    account.getOpeningDate(),
                    account.getStatus(),
                    ((CheckingAccount) account).getOverdraftLimit());
        }
        if(account instanceof SavingsAccount){
            return new SavingsAccountDto(account.getId(),
                    account.getAccountNumber(),
                    account.getBalance(),
                    account.getOpeningDate(),
                    account.getStatus(),
                    ((SavingsAccount) account).getInterestRate());
        }
        if(account instanceof InvestmentAccount){
            return new InvestmentAccountDto(account.getId(),
                    account.getAccountNumber(),
                    account.getBalance(),
                    account.getOpeningDate(),
                    account.getStatus(),
                    ((InvestmentAccount) account).getInvestmentObjective());
        }
        return null;
    }
}
