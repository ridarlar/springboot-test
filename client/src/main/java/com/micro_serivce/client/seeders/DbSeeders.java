package com.micro_serivce.client.seeders;
import com.micro_serivce.client.account.entity.AccountEntity;
import com.micro_serivce.client.account.repository.AccountRepository;
import com.micro_serivce.client.customer.entity.CustomerEntity;
import com.micro_serivce.client.customer.repository.CustomerRepository;
import com.micro_serivce.client.person.entity.PersonEntity;
import com.micro_serivce.client.person.repository.PersoneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbSeeders implements CommandLineRunner{
    private final PersoneRepository personRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public DbSeeders(PersoneRepository personRepository, CustomerRepository customerRepository, AccountRepository accountRepository){
        this.personRepository =  personRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
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

        AccountEntity seedAccout1ToCustomer1 = new AccountEntity("Saving", 100.0f, true);
        AccountEntity seedAccout2ToCustomer1 = new AccountEntity("Checking", 300.0f, true);
        AccountEntity seedAccout1ToCustomer2 = new AccountEntity("Saving", 200.0f, true);
        AccountEntity seedAccout2ToCustomer2 = new AccountEntity("Checking", 300.0f, true);
        AccountEntity seedAccout1ToCustomer3 = new AccountEntity("Saving", 400.0f, true);
        AccountEntity seedAccout2ToCustomer3 = new AccountEntity("Checking", 500.0f, true);
        AccountEntity seedAccout1ToCustomer4 = new AccountEntity("Saving", 600.0f, true);
        AccountEntity seedAccout2ToCustomer4 = new AccountEntity("Checking", 700.0f, true);
        AccountEntity seedAccout1ToCustomer5 = new AccountEntity("Saving", 800.0f, true);
        AccountEntity seedAccout2ToCustomer5 = new AccountEntity("Checking", 900.0f, true);

        seedAccout1ToCustomer1.setCustomer(seedCustomer1);
        seedAccout2ToCustomer1.setCustomer(seedCustomer1);
        seedAccout1ToCustomer2.setCustomer(seedCustomer2);
        seedAccout2ToCustomer2.setCustomer(seedCustomer2);
        seedAccout1ToCustomer3.setCustomer(seedCustomer3);
        seedAccout2ToCustomer3.setCustomer(seedCustomer3);
        seedAccout1ToCustomer4.setCustomer(seedCustomer4);
        seedAccout2ToCustomer4.setCustomer(seedCustomer4);
        seedAccout1ToCustomer5.setCustomer(seedCustomer5);
        seedAccout2ToCustomer5.setCustomer(seedCustomer5);

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

            accountRepository.save(seedAccout1ToCustomer1);
            accountRepository.save(seedAccout2ToCustomer1);
            accountRepository.save(seedAccout1ToCustomer2);
            accountRepository.save(seedAccout2ToCustomer2);
            accountRepository.save(seedAccout1ToCustomer3);
            accountRepository.save(seedAccout2ToCustomer3);
            accountRepository.save(seedAccout1ToCustomer4);
            accountRepository.save(seedAccout2ToCustomer4);
            accountRepository.save(seedAccout1ToCustomer5);
            accountRepository.save(seedAccout2ToCustomer5);


            System.out.println("Seeder for Clients created successfully.");
        }


    }
}
