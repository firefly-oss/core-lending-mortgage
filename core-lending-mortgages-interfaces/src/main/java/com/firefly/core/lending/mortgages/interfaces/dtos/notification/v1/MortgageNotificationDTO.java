/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.mortgages.interfaces.dtos.notification.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.notification.v1.NotificationTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.notification.v1.PriorityEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageNotificationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID notificationId;

    @FilterableId
    @NotNull(message = "Mortgage contract ID is required")
    private UUID mortgageContractId;

    @NotNull(message = "Notification type is required")
    private NotificationTypeEnum notificationType;

    @NotNull(message = "Priority is required")
    private PriorityEnum priority;

    @NotBlank(message = "Recipient is required")
    @Size(max = 200, message = "Recipient cannot exceed 200 characters")
    @Email(message = "Recipient must be a valid email address")
    private String recipient;

    @NotBlank(message = "Message is required")
    @Size(max = 2000, message = "Message cannot exceed 2000 characters")
    private String message;

    @NotNull(message = "Sent status is required")
    private Boolean isSent;

    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}