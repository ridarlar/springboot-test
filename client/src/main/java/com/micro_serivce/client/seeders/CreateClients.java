package com.micro_serivce.client.seeders;
import com.micro_serivce.client.customer.entity.CustomerEntity;
import com.micro_serivce.client.customer.repository.CustomerRepository;
import com.micro_serivce.client.person.entity.PersonEntity;
import com.micro_serivce.client.person.repository.PersoneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateClients implements CommandLineRunner{
    private final PersoneRepository personRepository;
    private final CustomerRepository customerRepository;

    public CreateClients(PersoneRepository personRepository, CustomerRepository customerRepository){
        this.personRepository =  personRepository;
        this.customerRepository = customerRepository;
    }

    public void run(String... args) throws Exception{
        PersonEntity seed1 = new PersonEntity("Richard", "Aguilar", "male", 24, 1205362452, "Solanda", 939074869);
        PersonEntity seed2 = new PersonEntity("Sofia", "Morales", "female", 28, 156589374, "Cumbayá", 955637492);
        PersonEntity seed3 = new PersonEntity("Carlos", "Ramirez", "male", 35, 1556547821, "La Carolina", 976431862);
        PersonEntity seed4 = new PersonEntity("Maria", "Gonzalez", "female", 30, 1086427395, "Quito", 982345600);
        PersonEntity seed5 = new PersonEntity("Luis", "Lopez", "male", 40, 1893465789, "Tumbaco", 997643211);

        CustomerEntity seedCustomer1 = new CustomerEntity("123456", true, seed1);
        CustomerEntity seedCustomer2 = new CustomerEntity("789012", true, seed2);
        CustomerEntity seedCustomer3 = new CustomerEntity("345678", true, seed3);
        CustomerEntity seedCustomer4 = new CustomerEntity("901234", true, seed4);
        CustomerEntity seedCustomer5 = new CustomerEntity("567890", true, seed5);

        if(personRepository.count() == 0){
            personRepository.save(seed1);
            personRepository.save(seed2);
            personRepository.save(seed3);
            personRepository.save(seed4);
            personRepository.save(seed5);

            customerRepository.save(seedCustomer1);
            customerRepository.save(seedCustomer2);
            customerRepository.save(seedCustomer3);
            customerRepository.save(seedCustomer4);
            customerRepository.save(seedCustomer5);

            System.out.println("Seeder for Clients created successfully.");
        }


    }
}
