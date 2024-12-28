package com.micro_serivce.client.customer.controller;

import com.micro_serivce.client.transaction.dto.AccountDataDto;
import com.micro_serivce.client.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> moneyTransfer(@RequestBody AccountDataDto accountDataDto){
        Long accountId = accountDataDto.getAccountId();
        Float balance = accountDataDto.getBalance();

        return transactionService.newTrnsaction(accountId, balance);
    }
}
