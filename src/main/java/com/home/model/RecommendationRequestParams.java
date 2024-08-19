package com.home.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendationRequestParams extends GeneralParams {
    @NotNull
    @Min(value = 1, message = "User Id must be greater than 0")
    private Integer userId;
    @NotNull
    @Min(value = 1, message = "Profile Id must be greater than 0")
    private Integer profileId;
}
