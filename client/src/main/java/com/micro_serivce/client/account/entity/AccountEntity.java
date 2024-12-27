package com.micro_serivce.client.account.entity;

import com.micro_serivce.client.customer.entity.CustomerEntity;
import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer accountNumber;

    private String type;

    private Float balance;

    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    public AccountEntity() {
    }

    public AccountEntity(String type, Float balance, Boolean state) {
        this.accountNumber =  generateRandomAccountNumber();;
        this.type = type;
        this.balance = balance;
        this.state = state;
    }

    private Integer generateRandomAccountNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
