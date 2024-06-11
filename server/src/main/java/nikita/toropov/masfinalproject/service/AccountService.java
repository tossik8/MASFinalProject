package nikita.toropov.masfinalproject.service;

import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.NewAccountData;
import nikita.toropov.masfinalproject.model.account.Account;
import nikita.toropov.masfinalproject.model.account.CheckingAccount;
import nikita.toropov.masfinalproject.model.account.InvestmentAccount;
import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.repository.account.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final ClientService clientService;
    private final AccountRepository accountRepository;

    public void createAccount(NewAccountData newAccountData){
        Client client = clientService.getClient(newAccountData.getClientId());
        String type = newAccountData.getType();
        Account account = switch (type) {
            case "checking" -> CheckingAccount.builder()
                    .owner(client)
                    .overdraftLimit(newAccountData.getOverdraftLimit())
                    .build();
            case "savings" -> SavingsAccount.builder()
                    .owner(client)
                    .interestRate(newAccountData.getInterestRate())
                    .build();
            case "investment" -> InvestmentAccount.builder()
                    .owner(client)
                    .investmentObjective(newAccountData.getInvestmentObjective())
                    .build();
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        };
        accountRepository.save(account);
    }
}
