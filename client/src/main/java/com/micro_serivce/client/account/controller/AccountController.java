package com.micro_serivce.client.account.controller;

import com.micro_serivce.client.account.entity.AccountEntity;
import com.micro_serivce.client.account.service.AccountSerivce;
import com.micro_serivce.client.customer.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        @RequestParam(defaultValue = "true") boolean state
    ){
        return accountSerivce.getAll(page,size, state);
    }

    @GetMapping("/{id}")
    public AccountEntity getAccountById(
            @PathVariable Long id
    ) {
        return accountSerivce.getAccountByIdOrClientId(id);
    }
}
