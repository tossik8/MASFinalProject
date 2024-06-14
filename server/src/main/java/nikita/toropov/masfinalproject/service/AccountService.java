package nikita.toropov.masfinalproject.service;

import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.NewAccountData;
import nikita.toropov.masfinalproject.model.account.Account;
import nikita.toropov.masfinalproject.model.account.CheckingAccount;
import nikita.toropov.masfinalproject.model.account.InvestmentAccount;
import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.repository.account.AccountRepository;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    /**
     * Creates a new account based on the provided data and associates it with a client.
     *
     * @param newAccountData The data required to create a new account, including client ID, account type, and relevant details.
     * @throws ResponseStatusException with HttpStatus.BAD_REQUEST if the account type is invalid.
     * @throws java.util.NoSuchElementException if a client is not found
     */
    public void createAccount(NewAccountData newAccountData){
        Client client = clientRepository.findById(newAccountData.getClientId()).orElseThrow();
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
