package com.micro_serivce.client.customer.entity;

import com.micro_serivce.client.account.entity.AccountEntity;
import com.micro_serivce.client.person.entity.PersonEntity;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private String password;

    private Boolean state;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity person;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountEntity> accounts;

    public CustomerEntity(){}

    public CustomerEntity(String password, Boolean state, PersonEntity person) {
        this.password = password;
        this.state = state;
        this.person = person;
        this.clientId = generateShortUUID();
    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getState() {
        return state;
    }

    public PersonEntity getPerson() {
        return person;
    }

    private String generateShortUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "");

        String base36UUID = new BigInteger(uuid, 16).toString(36);

        return base36UUID.substring(0, 6).toUpperCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
