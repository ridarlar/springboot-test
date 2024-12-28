package com.micro_serivce.client.transaction.entity;

import com.micro_serivce.client.account.entity.AccountEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    private Float lastBalance;

    private Float transactionBalance;

    public TransactionEntity() {
    }

    public TransactionEntity(AccountEntity account, Float lastBalance, Float transactionBalance) {
        this.account = account;
        this.lastBalance = lastBalance;
        this.transactionBalance = transactionBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public Float getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(Float lastBalance) {
        this.lastBalance = lastBalance;
    }

    public Float getTransactionBalance() {
        return transactionBalance;
    }

    public void setTransactionBalance(Float transactionBalance) {
        this.transactionBalance = transactionBalance;
    }

}
