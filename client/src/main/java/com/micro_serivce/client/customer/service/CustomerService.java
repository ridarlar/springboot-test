package com.micro_serivce.client.customer.service;

import com.micro_serivce.client.customer.entity.CustomerEntity;
import com.micro_serivce.client.customer.repository.CustomerRepository;
import com.micro_serivce.client.helpers.CustomExceptions.ResourceNotFoundException;
import com.micro_serivce.client.helpers.HelpersFunctions;
import com.micro_serivce.client.person.entity.PersonEntity;
import com.micro_serivce.client.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PersonService personService;

    public Page<CustomerEntity> getAllCostomers(int page, int size, boolean state){
        PageRequest pageable = PageRequest.of(page,size);
        return customerRepository.findByState(state, pageable);
    }

    public CustomerEntity getCustomerByIdOrClientId(String clientId){
        Optional<CustomerEntity> customer = Optional.empty();

        boolean isInteger = new HelpersFunctions().isInteger(clientId);

        if (isInteger) {
            Long id = Long.valueOf(clientId);
            return customerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Client with ID " + clientId + " not found"));
        }

        return customerRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client with ID " + clientId + " not found"));
    }

    public CustomerEntity save(CustomerEntity customer){
        Integer personDni = customer.getPerson().getDni();
        Integer personPhone = customer.getPerson().getPhoneNumber();

        personService.verifyDeplicateData(personDni, personPhone);

        PersonEntity personData = customer.getPerson();

        PersonEntity newPerson = personService.save(personData);
        CustomerEntity newCustomer = new CustomerEntity(
                customer.getPassword(),
                customer.getState(),
                newPerson
        );

        return customerRepository.save(newCustomer);
    }

    public CustomerEntity update(Long customerId, CustomerEntity customer){

        CustomerEntity currentCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + customerId));

        currentCustomer.setPassword(customer.getPassword());
        currentCustomer.setState(customer.getState());

        PersonEntity existingPerson = getPersonEntity(customer, currentCustomer);

        personService.save(existingPerson);

        customerRepository.save(currentCustomer);
        return currentCustomer;
    }

    private static PersonEntity getPersonEntity(CustomerEntity customer, CustomerEntity currentCustomer) {
        PersonEntity updatedPerson = customer.getPerson();
        PersonEntity currentPersone = currentCustomer.getPerson();

        Field[] fields = PersonEntity.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);

                Object updatedValue = field.get(updatedPerson);

                if (updatedValue != null) {
                    field.set(currentPersone, updatedValue);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return currentPersone;
    }

    public ResponseEntity<Void> delete(String clientId) {
        CustomerEntity currentCustomer = this.getCustomerByIdOrClientId(clientId);

        currentCustomer.setState(false);
        customerRepository.save(currentCustomer);

        return ResponseEntity.noContent().build();
    }
}
