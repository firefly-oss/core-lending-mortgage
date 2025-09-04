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


package com.firefly.core.lending.mortgages.models.entities.notification.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.notification.v1.NotificationTypeEnum;
import com.firefly.core.lending.mortgages.interfaces.enums.notification.v1.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_notification")
public class MortgageNotification {

    @Id
    @Column("notification_id")
    private UUID notificationId;

    @Column("mortgage_contract_id")
    private UUID mortgageContractId;

    @Column("notification_type")
    private NotificationTypeEnum notificationType;

    @Column("priority")
    private PriorityEnum priority;

    @Column("recipient")
    private String recipient;

    @Column("message")
    private String message;

    @Column("is_sent")
    private Boolean isSent;

    @Column("sent_at")
    private LocalDateTime sentAt;

    @Column("created_at")
    private LocalDateTime createdAt;
}