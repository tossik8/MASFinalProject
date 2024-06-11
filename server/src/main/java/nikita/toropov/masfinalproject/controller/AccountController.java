package nikita.toropov.masfinalproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nikita.toropov.masfinalproject.dto.NewAccountData;
import nikita.toropov.masfinalproject.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void createAccount(@Valid @RequestBody NewAccountData newAccountData){
        accountService.createAccount(newAccountData);
    }
}
