package com.example.keyvalue.controllers;

import com.example.keyvalue.model.KeyValueEntity;
import com.example.keyvalue.repository.KeyValueRepository;
import com.example.keyvalue.requestmodel.KeyValueRequestModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/movies")
public class KeyValueController {


    @Autowired
    KeyValueRepository keyValueRepository;

    ArrayList idList = new ArrayList<Integer>();


    @PostMapping
    public ResponseEntity<Void> postMovie(@RequestBody KeyValueRequestModel keyValueRequestModel) {


        KeyValueEntity keyValueEntity = new KeyValueEntity();


        BeanUtils.copyProperties(keyValueRequestModel, keyValueEntity);
        KeyValueEntity storedMovieDetails = keyValueRepository.save(keyValueEntity);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("id", storedMovieDetails.getMovieId()+"");

        if (storedMovieDetails == null)
            return null;

        idList.add(storedMovieDetails.getMovieId());

        return new ResponseEntity<>(httpHeaders,HttpStatus.CREATED) ;

    }


//	@RequestMapping(value="/{id}", method = RequestMethod.GET)

    @GetMapping(path = "/{id}")
    public ResponseEntity<KeyValueEntity> getMovies(@PathVariable int id) {

        KeyValueEntity userEntity = keyValueRepository.findByMovieId(id);

        if (userEntity == null)
            return null;

        return new ResponseEntity<KeyValueEntity>(userEntity , HttpStatus.OK);

    }

    @GetMapping(path = "/allMovies")
    public Object getIds() {
        if (idList != null)
            return idList;

        return null;
    }

    @GetMapping(path = "/test")
    public String test() {
        return "test all good";
    }


}