package com.example.keyvalue.repository;

import com.example.keyvalue.model.KeyValueEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeyValueRepository extends CrudRepository<KeyValueEntity, Long> {


	 KeyValueEntity findByMovieId(int movieId);


}
