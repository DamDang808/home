package com.home.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralParams {
    @NotNull
    private int zoneId;

    @NotNull
    private int contentFilter;

    @NotNull
    @Min(value = 1, message = "Limit must be greater than 0")
    private Integer limit;

    @NotNull
    @Min(value = 0, message = "Offset must be greater than or equal to 0")
    private Integer offset;
}
