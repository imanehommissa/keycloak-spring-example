//package com.example.controllers;
//
//import com.example.keyvalue.controllers.KeyValueController;
//import com.example.keyvalue.model.KeyValueEntity;
//import com.example.keyvalue.repository.KeyValueRepository;
//import com.example.keyvalue.requestmodel.KeyValueRequestModel;
//import com.example.keyvalue.services.KeyCloakService;
//import org.junit.jupiter.api.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//
//public class KeyValueControllerTest {
//
//
//    @InjectMocks
//    KeyValueController keyValueController;
//
//
//
//    @Mock
//    KeyValueRepository keyValueRepository;
//
//    @Mock
//    KeyCloakService keyCloakService;
//
//
//    KeyValueRequestModel keyValueRequestModel;
//    KeyValueEntity keyValueEntity;
//
//
//    static double av = 0;
//    static double step = 0;
//
//
//    static TestReporter testReporter;
//    static TestInfo testInfo;
//
//    @BeforeAll
//    static void init() {
//    }
//
//    @BeforeEach
//    void initEach(TestReporter testReporter, TestInfo testInfo) {
//
//        this.testReporter = testReporter;
//        this.testInfo = testInfo;
//
//        MockitoAnnotations.initMocks(this);
//
//        keyValueEntity = new KeyValueEntity();
//        keyValueEntity.setMovieName("the originals");
//        keyValueEntity.setFavActor("klaus");
//
//
//        keyValueRequestModel = new KeyValueRequestModel();
//        keyValueRequestModel.setMovieName("the originals");
//        keyValueRequestModel.setFavActor("klaus");
//
//    }
//
//    @RepeatedTest(100)
//    @DisplayName("POST movie")
//    @Tag("post")
//    void postMovie(RepetitionInfo repetitionInfo) {
//
//        calculateRequestAvgTime(repetitionInfo);
//        when(keyValueRepository.save(any(KeyValueEntity.class))).thenReturn(keyValueEntity);
//        assertNotNull(keyValueController.postMovie(keyValueRequestModel));
//
//    }
//
//    @Tag("get")
//    @DisplayName("GET movie actor")
//    @RepeatedTest(100)
//    void getMovies(RepetitionInfo repetitionInfo) {
//        calculateRequestAvgTime(repetitionInfo);
//
//        when(keyValueRepository.findByMovieId(anyInt())).thenReturn(keyValueEntity);
//        assertNotNull(keyValueController.getMovies(anyInt()));
//    }
//
//
//    @Test
//    public void getMoviesIds() {
//
//
//    }
//
//    @AfterAll
//    static void clean() {
//
//        System.out.println(testInfo.getTags());
//
//        if (testInfo.getTags().contains("get")) {
//
//            av /= 99;
//        } else {
//            av /= 99;
//
//        }
//
//        av = av * 1000;
//
//        String fAverage = (String.valueOf(av)).substring(0, 6) + "ms";
//
//
//        System.out.println(fAverage);
//    }
//
//    public void calculateRequestAvgTime(RepetitionInfo repetitionInfo) {
//
//        double ts = Double.valueOf(java.time.LocalTime.now().toString().substring(8));
//
//        if (repetitionInfo.getCurrentRepetition() > 1) {
//            if (step > ts) {
//                step = 1 - (step - ts);
//            } else {
//                step = ts - step;
//
//            }
//            av += step;
//            step = ts;
//        } else if (repetitionInfo.getCurrentRepetition() == 1) {
//            step += ts;
//
//        }
//    }
//}