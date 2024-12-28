package com.micro_serivce.client.account.controller;

import com.micro_serivce.client.account.entity.AccountEntity;
import com.micro_serivce.client.account.service.AccountSerivce;
import com.micro_serivce.client.customer.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountSerivce accountSerivce;

    @GetMapping
    public Page<AccountEntity> getAllAccounts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "2") int size,
        @RequestParam(defaultValue = "true") boolean state,
        @RequestParam(defaultValue = "0") Long clientId
    ){
        return accountSerivce.getAll(page,size, state, clientId);
    }

    @GetMapping("/{id}")
    public AccountEntity getAccountById(
            @PathVariable Long id
    ) {
        return accountSerivce.getAccountByIdOrClientId(id);
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<AccountEntity> saveOrUpdateCustomer(@PathVariable String clientId,@RequestBody AccountEntity account) {
        accountSerivce.create(clientId, account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountEntity> updateCustomer(@PathVariable Long id, @RequestBody AccountEntity customer) {
        AccountEntity updated = accountSerivce.update(id, customer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id
    ) {
        return accountSerivce.delete(id);
    }
}
