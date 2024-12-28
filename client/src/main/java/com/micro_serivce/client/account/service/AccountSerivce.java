package com.micro_serivce.client.account.service;

import com.micro_serivce.client.account.entity.AccountEntity;
import com.micro_serivce.client.account.repository.AccountRepository;
import com.micro_serivce.client.customer.entity.CustomerEntity;
import com.micro_serivce.client.customer.service.CustomerService;
import com.micro_serivce.client.helpers.CustomExceptions.ResourceNotFoundException;
import com.micro_serivce.client.helpers.HelpersFunctions;
import com.micro_serivce.client.person.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountSerivce {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerService customerService;

    public Page<AccountEntity> getAll(int page, int size, boolean state,Long clientId){
        PageRequest pageable = PageRequest.of(page,size);

        if(clientId>0){
            return accountRepository.findByStateAndCustomerId(state, pageable, clientId);
        }
        return accountRepository.findByState(state, pageable);
    }

    public AccountEntity getAccountByIdOrClientId(Long accountId) {

        Integer accountIdAsInteger = accountId.intValue();

        return accountRepository.findByAccountNumberOrId(accountIdAsInteger, accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + accountId + " not found"));

    }

    public AccountEntity update(Long id, AccountEntity customer) {
        AccountEntity currentAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + id + " not found"));
        currentAccount.setType(customer.getType());
        currentAccount.setState(customer.getState());
        currentAccount.setBalance(customer.getBalance());

        return accountRepository.save(currentAccount);
    }

    public ResponseEntity<Void> delete(Long id) {
        AccountEntity currentAccount = this.accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + id + " not found"));

        currentAccount.setState(false);
        accountRepository.save(currentAccount);

        return ResponseEntity.noContent().build();
    }

    public AccountEntity saveAccount(AccountEntity accountToSave){
        return accountRepository.save(accountToSave);
    }

    public void create(String clinetId, AccountEntity account) {
        CustomerEntity currentCustomer = customerService.getCustomerByIdOrClientId(clinetId);

        AccountEntity newAccount = new AccountEntity(
                account.getType(),
                account.getBalance(),
                account.getState()
        );

        newAccount.setCustomer(currentCustomer);
        accountRepository.save(newAccount);
    }
}
