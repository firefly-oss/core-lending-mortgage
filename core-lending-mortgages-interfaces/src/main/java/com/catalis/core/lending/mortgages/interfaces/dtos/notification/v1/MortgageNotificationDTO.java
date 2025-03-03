package com.catalis.core.lending.mortgages.interfaces.dtos.notification.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.mortgages.interfaces.enums.notification.v1.NotificationTypeEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.notification.v1.PriorityEnum;
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
public class MortgageNotificationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long notificationId;

    @FilterableId
    private Long mortgageContractId;

    private NotificationTypeEnum notificationType;
    private PriorityEnum priority;
    private String recipient;
    private String message;
    private Boolean isSent;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}