package com.micro_serivce.client.transaction.repository;

import com.micro_serivce.client.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
}
