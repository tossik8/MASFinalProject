package nikita.toropov.masfinalproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.account.AccountCreationDto;
import nikita.toropov.masfinalproject.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    /**
     * Creates a new account using the validated data from {@code AccountCreationDto}.
     *
     * @param accountCreationDto the data transfer object containing account creation details.
     */
    @PostMapping
    public void createAccount(@Valid @RequestBody AccountCreationDto accountCreationDto){
        accountService.createAccount(accountCreationDto);
    }
}
