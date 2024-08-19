package com.home.model;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetDataRequestParams extends GeneralParams {

    private List<Integer> list;

    public GetDataRequestParams(int zoneId, int contentFilter, int limit, int offset, List<Integer> listIds) {
        super(zoneId, contentFilter, limit, offset);
        this.list = listIds;
    }
}
