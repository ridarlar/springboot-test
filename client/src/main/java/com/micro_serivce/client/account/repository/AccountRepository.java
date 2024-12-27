package com.micro_serivce.client.account.repository;

import com.micro_serivce.client.account.entity.AccountEntity;
import com.micro_serivce.client.customer.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Page<AccountEntity> findByState(boolean state, PageRequest pageable);

    Optional<AccountEntity> findByAccountNumberOrId(Integer accountNumber, Long id);
}
