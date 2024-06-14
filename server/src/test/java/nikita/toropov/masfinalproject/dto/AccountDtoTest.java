package nikita.toropov.masfinalproject.dto;

import nikita.toropov.masfinalproject.dto.account.CheckingAccountDto;
import nikita.toropov.masfinalproject.dto.account.InvestmentAccountDto;
import nikita.toropov.masfinalproject.dto.account.SavingsAccountDto;
import nikita.toropov.masfinalproject.model.account.CheckingAccount;
import nikita.toropov.masfinalproject.model.account.InvestmentAccount;
import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import nikita.toropov.masfinalproject.repository.account.AccountRepository;
import nikita.toropov.masfinalproject.service.AccountDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AccountDtoTest {

    private final AccountDtoMapper accountDtoMapper = new AccountDtoMapper();
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testCheckingAccountToDto(){
        CheckingAccount account = (CheckingAccount) accountRepository.findById(1000L).orElseThrow();
        CheckingAccountDto checkingAccountDto = (CheckingAccountDto) accountDtoMapper.apply(account);

        assertEquals(account.getId(), checkingAccountDto.getId());
        assertEquals(account.getAccountNumber(), checkingAccountDto.getAccountNumber());
        assertEquals(account.getStatus(), checkingAccountDto.getStatus());
        assertEquals(account.getBalance(), checkingAccountDto.getBalance());
        assertEquals(account.getOpeningDate(), checkingAccountDto.getOpeningDate());
        assertEquals(account.getOverdraftLimit(), checkingAccountDto.getOverdraftLimit());
    }

    @Test
    public void testSavingsAccountToDto(){
        SavingsAccount account = (SavingsAccount) accountRepository.findById(1002L).orElseThrow();
        SavingsAccountDto checkingAccountDto = (SavingsAccountDto) accountDtoMapper.apply(account);

        assertEquals(account.getId(), checkingAccountDto.getId());
        assertEquals(account.getAccountNumber(), checkingAccountDto.getAccountNumber());
        assertEquals(account.getStatus(), checkingAccountDto.getStatus());
        assertEquals(account.getBalance(), checkingAccountDto.getBalance());
        assertEquals(account.getOpeningDate(), checkingAccountDto.getOpeningDate());
        assertEquals(account.getInterestRate(), checkingAccountDto.getInterestRate());
    }

    @Test
    public void testInvestmentAccountToDto(){
        InvestmentAccount account = (InvestmentAccount) accountRepository.findById(1003L).orElseThrow();
        InvestmentAccountDto checkingAccountDto = (InvestmentAccountDto) accountDtoMapper.apply(account);

        assertEquals(account.getId(), checkingAccountDto.getId());
        assertEquals(account.getAccountNumber(), checkingAccountDto.getAccountNumber());
        assertEquals(account.getStatus(), checkingAccountDto.getStatus());
        assertEquals(account.getBalance(), checkingAccountDto.getBalance());
        assertEquals(account.getOpeningDate(), checkingAccountDto.getOpeningDate());
        assertEquals(account.getInvestmentObjective(), checkingAccountDto.getInvestmentObjective());
    }
}
