package com.micro_serivce.client.transaction.dto;

public class AccountDataDto {

    private Long accountId;

    private Float balance;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
