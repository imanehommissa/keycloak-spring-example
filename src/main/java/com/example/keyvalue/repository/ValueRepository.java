package com.example.keyvalue.repository;

import com.example.keyvalue.model.ValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepository extends JpaRepository<ValueEntity,Integer> {


}
