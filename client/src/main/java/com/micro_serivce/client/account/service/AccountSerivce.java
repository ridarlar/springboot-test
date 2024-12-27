package com.micro_serivce.client.account.service;

import com.micro_serivce.client.account.entity.AccountEntity;
import com.micro_serivce.client.account.repository.AccountRepository;
import com.micro_serivce.client.customer.entity.CustomerEntity;
import com.micro_serivce.client.helpers.CustomExceptions.ResourceNotFoundException;
import com.micro_serivce.client.helpers.HelpersFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountSerivce {
    @Autowired
    AccountRepository accountRepository;

    public Page<AccountEntity> getAll(int page, int size, boolean state){
        PageRequest pageable = PageRequest.of(page,size);
        return accountRepository.findByState(state, pageable);
    }

    public AccountEntity getAccountByIdOrClientId(Long accountId) {

        Integer accountIdAsInteger = accountId.intValue();

        return accountRepository.findByAccountNumberOrId(accountIdAsInteger, accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + accountId + " not found"));

    }

}
