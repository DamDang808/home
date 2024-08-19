package com.home.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RelatedRequestParams extends GeneralParams {
    @NotNull
    @Min(value = 1, message = "Id must be greater than 0")
    private Integer id;
}
