package com.micro_serivce.client.customer.repository;

import com.micro_serivce.client.customer.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    List<CustomerEntity> findAll();

    Optional<CustomerEntity> findByClientId(String clientId);

    Page<CustomerEntity> findByState(boolean state, PageRequest pageable);

}
