package nikita.toropov.masfinalproject.service;

import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.account.AccountCreationDto;
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
     * Creates a new account based on the provided {@code AccountCreationDto}.
     *
     * @param accountCreationDto the data transfer object containing account creation details,
     *                           including client ID and specific account type details.
     * @throws java.util.NoSuchElementException if no client with the specified ID is found in the repository.
     * @throws ResponseStatusException if the account type specified in {@code accountCreationDto}
     *                                 is not recognized, resulting in a HTTP 400 (Bad Request) response.
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
