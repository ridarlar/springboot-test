package com.micro_serivce.client.customer.service;

import com.micro_serivce.client.customer.entity.CustomerEntity;
import com.micro_serivce.client.customer.repository.CustomerRepository;
import com.micro_serivce.client.person.entity.PersonEntity;
import com.micro_serivce.client.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Optional<CustomerEntity> getCustomerByIdOrClientId(String clientId){
        Optional<CustomerEntity> customer = Optional.empty();

        boolean isInteger = isInteger(clientId);

        if (isInteger) {
            try {
                customer = customerRepository.findById(Long.valueOf(clientId));
            } catch (NumberFormatException e) {
                customer = Optional.empty();
            }
        }

        if (customer.isEmpty()) {
            customer = customerRepository.findByClientId(clientId);
        }

        return customer;
    }

    public CustomerEntity save(CustomerEntity customer){
        return customerRepository.save(customer);
    }

    public CustomerEntity update(Long customerId, CustomerEntity customer){

        CustomerEntity currentCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + customerId));

        currentCustomer.setPassword(customer.getPassword());
        currentCustomer.setState(customer.getState());

        PersonEntity existingPerson = getPersonEntity(customer, currentCustomer);

        personService.saveOrUpdate(existingPerson);

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

    public boolean isInteger(String inputValue){
        try{
            Integer.parseInt(inputValue);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public ResponseEntity<Void> delete(String clientId) {
        Optional<CustomerEntity> currentCustomer = this.getCustomerByIdOrClientId(clientId);

        currentCustomer.ifPresent(customer -> {
            customer.setState(false);
            customerRepository.save(customer);
        });

        return ResponseEntity.noContent().build();
    }
}
