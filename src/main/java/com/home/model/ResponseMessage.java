package com.home.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseMessage {
    private String message;
    private int status;
    private List<Object> data;
}
