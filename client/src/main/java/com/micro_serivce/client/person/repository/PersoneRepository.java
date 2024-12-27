package com.micro_serivce.client.person.repository;

import com.micro_serivce.client.person.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersoneRepository extends JpaRepository<PersonEntity,Long> {
}
