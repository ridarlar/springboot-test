package com.micro_serivce.client.person.service;

import com.micro_serivce.client.helpers.CustomExceptions.DuplicateDataException;
import com.micro_serivce.client.person.entity.PersonEntity;
import com.micro_serivce.client.person.repository.PersoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersoneRepository personRepository;

    public List<PersonEntity> getPersons(){
        return personRepository.findAll();
    }

    public Optional<PersonEntity> getPerson(Long id){
        return personRepository.findById(id);
    }

    public PersonEntity save(PersonEntity person){
        return personRepository.save(person);
    }

    public void remove(Long id){
        personRepository.deleteById(id);
    }

    public void verifyDeplicateData(Integer personDni, Integer personPhone) {
        boolean dniExists = personRepository.existsByDni(personDni);

        if(dniExists){
            throw  new DuplicateDataException("A client with DNI already exists.");
        }

        boolean phoneExists = personRepository.existsByPhoneNumber(personPhone);

        if(phoneExists) {
            throw new DuplicateDataException("A client with this phone number already exists.");
        }
    }
}
