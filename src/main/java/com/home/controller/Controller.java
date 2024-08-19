package com.home.controller;

import com.home.model.RecommendationRequestParams;
import com.home.model.RelatedRequestParams;
import com.home.model.ResponseMessage;
import com.home.service.HomeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class Controller {

    @Autowired
    private HomeService homeService;

    @GetMapping("/recommendation/{type}")
    public ResponseEntity<ResponseMessage> getRecommendation(@PathVariable String type,
                                                             @Valid @RequestBody RecommendationRequestParams params,
                                                             @RequestHeader("Authorization") String token) {
        return homeService.getRecommendation(type, params, token);
    }

    @GetMapping("/related/{type}")
    public ResponseEntity<ResponseMessage> getRelated(@PathVariable String type,
                                                      @Valid @RequestBody RelatedRequestParams params,
                                                      @RequestHeader("Authorization") String token) {
        return homeService.getRelated(type, params, token);
    }
}
