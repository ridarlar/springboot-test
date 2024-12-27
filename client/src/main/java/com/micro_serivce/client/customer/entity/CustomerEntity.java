package com.micro_serivce.client.customer.entity;

import com.micro_serivce.client.person.entity.PersonEntity;
import jakarta.persistence.*;

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
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid.substring(0,6);
    }

    public void setId(String id) {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
