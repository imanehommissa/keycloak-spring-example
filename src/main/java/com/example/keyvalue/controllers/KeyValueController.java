package com.example.keyvalue.controllers;

import com.example.keyvalue.exception.KeyNotFoundException;
import com.example.keyvalue.model.KeyEntity;
import com.example.keyvalue.model.ValueEntity;
import com.example.keyvalue.repository.KeyRepository;
import com.example.keyvalue.repository.ValueRepository;
import com.example.keyvalue.services.KeyCloakService;
import com.fasterxml.jackson.annotation.JsonView;
import org.keycloak.adapters.spi.AuthOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/keys")
public class KeyValueController {


    @Autowired
    KeyRepository keyRepository;

    @Autowired
    ValueRepository valueRepository;


    @Autowired
    KeyCloakService keyCloakService;

    ArrayList idList = new ArrayList<Integer>();


    @PostMapping
    public ResponseEntity<Void> createKey(@Valid @RequestBody KeyEntity key, HttpServletRequest request) {


        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            KeyEntity savedKey = keyRepository.save(key);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("id", savedKey.getId() + "");


            idList.add(savedKey.getId());

            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);

        } else {
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }
    }


    @GetMapping(path = "/{id}")
    @JsonView(KeyEntity.Views.Internal.class)
    public ResponseEntity getKey(@PathVariable int id, HttpServletRequest request) {

        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            Optional<KeyEntity> key = keyRepository.findById(id);


            if (!key.isPresent())
                throw new KeyNotFoundException("id-" + id);

            return new ResponseEntity(key, HttpStatus.OK);
        } else {
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }

    }

    @GetMapping
    @JsonView(KeyEntity.Views.Public.class)
    public ResponseEntity retrieveAllKeys(HttpServletRequest request) {
        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            List<KeyEntity> keys = keyRepository.findAll();


            return new ResponseEntity(keys, HttpStatus.OK);
        } else {
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteKey(@PathVariable int id, HttpServletRequest request) {
        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            keyRepository.deleteById(id);
            return new ResponseEntity(String.format("key of id %s is is deleted", id), HttpStatus.OK);

        } else {
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }

    }

    @PostMapping("/{id}/values")
    public ResponseEntity createValue(@PathVariable int id, @RequestBody ValueEntity value, HttpServletRequest request) {

        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            Optional<KeyEntity> keyOptional = keyRepository.findById(id);

            if (!keyOptional.isPresent()) {
                throw new KeyNotFoundException("id-" + id);
            }

            KeyEntity key = keyOptional.get();

            value.setKey(key);

            valueRepository.save(value);

//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(value.getId())
//                    .toUri();

//            return ResponseEntity.created(location).build();
            return new ResponseEntity("",HttpStatus.OK);
        } else {
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }

    }

    @GetMapping("/{id}/values")
    public ResponseEntity<ValueEntity> retrieveAllValues(@PathVariable int id, HttpServletRequest request) {

        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            Optional<KeyEntity> keyOptional = keyRepository.findById(id);

            if (!keyOptional.isPresent()) {
                throw new KeyNotFoundException("id-" + id);
            }

            return new ResponseEntity(keyOptional.get().getValues(), HttpStatus.OK);
        } else {
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }
    }


}