package io.quind.technicaltesthexagonal.modules.account.infrastructure.controllers;

import io.quind.technicaltesthexagonal.modules.account.application.services.AccountService;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.UpdateAccountStatusRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest){
        return ResponseEntity.ok(accountService.createAccount(accountRequest));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(){
        List<AccountResponse> accountResponses = accountService.findAll();
        if (accountResponses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountResponses);
    }

    @GetMapping("/{account-number}")
    public ResponseEntity<AccountResponse> getAccountByAccountNumber(@PathVariable("account-number") String accountNumber){
        return accountService.findByAccountNumber(accountNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{account-number}/status")
    public ResponseEntity<AccountResponse> updateAccountStatus(@PathVariable("account-number") String accountNumber, @Valid @RequestBody UpdateAccountStatusRequest updateAccountStatusRequest){
        return ResponseEntity.ok(accountService.updateAccountStatus(accountNumber, updateAccountStatusRequest));
    }

    @PatchMapping("/{account-number}/cancel")
    public ResponseEntity<Void> deleteAccount(@PathVariable("account-number") String accountNumber){
        accountService.cancelAccount(accountNumber);
        return ResponseEntity.noContent().build();
    }

}
