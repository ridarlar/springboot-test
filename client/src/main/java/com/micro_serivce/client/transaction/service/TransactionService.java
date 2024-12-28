package com.micro_serivce.client.transaction.service;

import com.micro_serivce.client.account.entity.AccountEntity;
import com.micro_serivce.client.account.service.AccountSerivce;
import com.micro_serivce.client.helpers.CustomExceptions.CreationFailedException;
import com.micro_serivce.client.transaction.entity.TransactionEntity;
import com.micro_serivce.client.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.cert.CertificateEncodingException;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountSerivce accountSerivce;

    public ResponseEntity<Void> newTrnsaction(Long accountId, Float balance) {
        AccountEntity currentCustomer = accountSerivce.getAccountByIdOrClientId(accountId);

        TransactionEntity newTransaction = new TransactionEntity();
        newTransaction.setTransactionBalance(balance);
        newTransaction.setLastBalance(currentCustomer.getBalance());


        if(balance==0){
            throw  new CreationFailedException("El valor de la transacción no debe ser 0 o nulo.");
        }

        if(balance>0){
            currentCustomer.addBalance(balance);
        }

        if(balance<0){

            if( currentCustomer.getBalance()  < Math.abs(balance)){
                throw  new CreationFailedException("El cliente no tiene saldo suficiente.");
            }

            currentCustomer.subtrabBalance(balance);
        }

        transactionRepository.save(newTransaction);
        accountSerivce.saveAccount(currentCustomer);

        return ResponseEntity.noContent().build();
    }
}
