package com.firefly.core.lending.mortgages.interfaces.dtos.application.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.application.v1.ApplicationStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageApplicationStatusHistoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long statusHistoryId;

    @FilterableId
    private Long mortgageApplicationId;

    private ApplicationStatusEnum oldStatus;
    private ApplicationStatusEnum newStatus;
    private String changedBy;
    private String changeReason;
    private String comments;
    private LocalDateTime changedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}