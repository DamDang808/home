package com.home.service;

import com.google.gson.Gson;
import com.home.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HomeService {
    @Autowired
    private RestTemplate restTemplate;

    private static final String API_GATEWAY_RECOMMENDATION = "http://api-gateway/recommendation/";
    private static final String API_GATEWAY_RELATED = "http://api-gateway/related/";
    private static final String API_GATEWAY_GET_DATA = "http://api-gateway/get-data/";
    private final Gson gson = new Gson(); // Initialize Gson once

    public ResponseEntity<ResponseMessage> getRecommendation(String type, RecommendationRequestParams params, String token) {
        return getResponse(API_GATEWAY_RECOMMENDATION + type, params, token);
    }

    public ResponseEntity<ResponseMessage> getRelated(String type, RelatedRequestParams params, String token) {
        return getResponse(API_GATEWAY_RELATED + type, params, token);
    }

    // Generalized method to handle both recommendation and related requests
    private ResponseEntity<ResponseMessage> getResponse(String url, GeneralParams params, String token) {
        HttpEntity<String> entity = getHttpEntity(token, params);
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseMessage.class);
        return getMediaData(response, token, url.substring(url.lastIndexOf("/") + 1), params.getContentFilter(), params.getZoneId(), params.getLimit(), params.getOffset());
    }

    private ResponseEntity<ResponseMessage> getMediaData(ResponseEntity<ResponseMessage> response,
                                                         String token,
                                                         String type,
                                                         int contentFilter,
                                                         int zoneId,
                                                         int limit,
                                                         int offset) {
        // Use streams for more concise conversion to list of integers
        List<Integer> listIds = response.getBody().getData().stream()
                .map(id -> (Integer) id)
                .collect(Collectors.toList());

        // Create a request body object
        GeneralParams requestParams = new GetDataRequestParams(zoneId, contentFilter, limit, offset, listIds);


        HttpEntity<String> getDataEntity = getHttpEntity(token, requestParams);
        return restTemplate.exchange(API_GATEWAY_GET_DATA + type, HttpMethod.POST, getDataEntity, ResponseMessage.class);
    }

    private HttpEntity<String> getHttpEntity(String token, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = gson.toJson(body);
        return new HttpEntity<>(requestBody, headers);
    }
}