package com.example.controllers;

import com.example.keyvalue.controllers.KeyValueController;

import com.example.keyvalue.model.KeyEntity;
import com.example.keyvalue.model.ValueEntity;
import com.example.keyvalue.repository.KeyRepository;
import com.example.keyvalue.repository.ValueRepository;
import com.example.keyvalue.services.KeyCloakService;
import org.junit.jupiter.api.*;
import org.keycloak.adapters.spi.AuthOutcome;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


public class KeyValueControllerTest {


    @InjectMocks
    KeyValueController keyValueController;



    @Mock
    KeyRepository keyRepository;

    @Mock
    ValueRepository valueRepository;

    @Mock
    KeyCloakService keyCloakService;


    KeyEntity keyEntity;
    ValueEntity valueEntity;
    HttpServletRequest request;


    static double av = 0;
    static double step = 0;


    static TestReporter testReporter;
    static TestInfo testInfo;

    @BeforeAll
    static void init() {
    }

    @BeforeEach
    void initEach(TestReporter testReporter, TestInfo testInfo) {

        this.testReporter = testReporter;
        this.testInfo = testInfo;

        MockitoAnnotations.initMocks(this);

        keyEntity = new KeyEntity();
        keyEntity.setKeyName("movie");
        valueEntity=new ValueEntity();
        valueEntity.setValue("original");
        valueEntity.setKey(keyEntity);
//        keyEntity.setValues((List<ValueEntity>) valueEntity);



    }

    @RepeatedTest(100)
    @DisplayName("POST key")
    @Tag("post")
    void postKey(RepetitionInfo repetitionInfo) {

        calculateRequestAvgTime(repetitionInfo);
        when(keyCloakService.authenticate(request)).thenReturn(AuthOutcome.AUTHENTICATED);
        when(keyRepository.save(any(KeyEntity.class))).thenReturn(keyEntity);

        assertNotNull(keyValueController.createKey(keyEntity,request));

    }

    @RepeatedTest(100)
    @DisplayName("POST key")
    @Tag("post")
    void postKeyValue(RepetitionInfo repetitionInfo) {

        calculateRequestAvgTime(repetitionInfo);
        when(keyCloakService.authenticate(request)).thenReturn(AuthOutcome.AUTHENTICATED);
        when(keyRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(keyEntity));
        when(valueRepository.save(any(ValueEntity.class))).thenReturn(valueEntity);

//        when(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(anyInt())
//                .toUri()).thenReturn(null);

        assertNotNull(keyValueController.createValue(anyInt(),valueEntity,request));

    }

    @Tag("get")
    @DisplayName("GET key")
    @RepeatedTest(100)
    void getKey(RepetitionInfo repetitionInfo) {
        calculateRequestAvgTime(repetitionInfo);
        when(keyCloakService.authenticate(request)).thenReturn(AuthOutcome.AUTHENTICATED);

        when(keyRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(keyEntity));
        assertNotNull(keyValueController.getKey(anyInt(),request));
    }


    @Test
    public void getMoviesIds() {


    }

    @AfterAll
    static void clean() {

        System.out.println(testInfo.getTags());

        if (testInfo.getTags().contains("get")) {

            av /= 99;
        } else {
            av /= 99;

        }

        av = av * 1000;

        String fAverage = (String.valueOf(av)).substring(0, 6) + "ms";


        System.out.println(fAverage);
    }

    public void calculateRequestAvgTime(RepetitionInfo repetitionInfo) {

        double ts = Double.valueOf(java.time.LocalTime.now().toString().substring(8));

        if (repetitionInfo.getCurrentRepetition() > 1) {
            if (step > ts) {
                step = 1 - (step - ts);
            } else {
                step = ts - step;

            }
            av += step;
            step = ts;
        } else if (repetitionInfo.getCurrentRepetition() == 1) {
            step += ts;

        }
    }
}