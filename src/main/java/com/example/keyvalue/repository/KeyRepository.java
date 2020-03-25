package com.example.keyvalue.repository;

import com.example.keyvalue.model.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<KeyEntity,Integer> {



}
