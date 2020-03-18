package com.example.keyvalue.controllers;

import com.example.keyvalue.model.KeyValueEntity;
import com.example.keyvalue.repository.KeyValueRepository;
import com.example.keyvalue.requestmodel.KeyValueRequestModel;
import com.example.keyvalue.services.KeyCloakService;
import org.keycloak.adapters.spi.AuthOutcome;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@RestController
@RequestMapping("/movies")
public class KeyValueController {


    @Autowired
    KeyValueRepository keyValueRepository;


    @Autowired
    KeyCloakService keyCloakService;

    ArrayList idList = new ArrayList<Integer>();


    @PostMapping
    public ResponseEntity<Void> postMovie(@RequestBody KeyValueRequestModel keyValueRequestModel, HttpServletRequest request) {


        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {
            KeyValueEntity keyValueEntity = new KeyValueEntity();


            BeanUtils.copyProperties(keyValueRequestModel, keyValueEntity);
            KeyValueEntity storedMovieDetails = keyValueRepository.save(keyValueEntity);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("id", storedMovieDetails.getMovieId() + "");

            if (storedMovieDetails == null)
                return null;

            idList.add(storedMovieDetails.getMovieId());

            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);

        }else{
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }
    }


//	@RequestMapping(value="/{id}", method = RequestMethod.GET)

    @GetMapping(path = "/{id}")
    public ResponseEntity<KeyValueEntity> getMovies(@PathVariable int id,HttpServletRequest request) {

        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            KeyValueEntity userEntity = keyValueRepository.findByMovieId(id);

            if (userEntity == null)
                return null;

            return new ResponseEntity<KeyValueEntity>(userEntity, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }

    }

    @GetMapping(path = "/allMovies")
    public Object getIds(HttpServletRequest request) {
        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {
            if (idList != null)
            return idList;

        return null;
        }else{
            return new ResponseEntity("Hi!, you are NOT auhorized !", HttpStatus.UNAUTHORIZED);

        }

    }

    @GetMapping(path = "/test")
    public String test(HttpServletRequest request) {
        if (keyCloakService.authenticate(request).equals(AuthOutcome.AUTHENTICATED)) {

            return "test all good";
        }else{
            return "Hi!, you are NOT auhorized";

        }
    }


}