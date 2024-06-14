package nikita.toropov.masfinalproject.service;

import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.AccountCreationDto;
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
     * @param accountCreationDto The data required to create a new account, including client ID, account type, and relevant details.
     * @throws ResponseStatusException with HttpStatus.BAD_REQUEST if the account type is invalid.
     * @throws java.util.NoSuchElementException if a client is not found
     */
    public void createAccount(AccountCreationDto accountCreationDto){
        Client client = clientRepository.findById(accountCreationDto.getClientId()).orElseThrow();
        String type = accountCreationDto.getType();
        Account account = switch (type) {
            case "checking" -> CheckingAccount.builder()
                    .owner(client)
                    .overdraftLimit(accountCreationDto.getOverdraftLimit())
                    .build();
            case "savings" -> SavingsAccount.builder()
                    .owner(client)
                    .interestRate(accountCreationDto.getInterestRate())
                    .build();
            case "investment" -> InvestmentAccount.builder()
                    .owner(client)
                    .investmentObjective(accountCreationDto.getInvestmentObjective())
                    .build();
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        };
        accountRepository.save(account);
    }
}
