package com.micro_serivce.client.person.service;

import com.micro_serivce.client.person.entity.PersonEntity;
import com.micro_serivce.client.person.repository.PersoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersoneRepository personRepository;

    public List<PersonEntity> getPersons(){
        return personRepository.findAll();
    }

    public Optional<PersonEntity> getPerson(Long id){
        return personRepository.findById(id);
    }

    public void saveOrUpdate(PersonEntity person){
        personRepository.save(person);
    }

    public void remove(Long id){
        personRepository.deleteById(id);
    }
}
