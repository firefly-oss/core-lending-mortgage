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


package com.firefly.core.lending.mortgages.models.entities.application.v1;

import com.firefly.core.lending.mortgages.interfaces.enums.application.v1.ApplicationStatusEnum;
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
@Table("mortgage_application_status_history")
public class MortgageApplicationStatusHistory {

    @Id
    @Column("status_history_id")
    private UUID statusHistoryId;

    @Column("mortgage_application_id")
    private UUID mortgageApplicationId;

    @Column("old_status")
    private ApplicationStatusEnum oldStatus;

    @Column("new_status")
    private ApplicationStatusEnum newStatus;

    @Column("changed_by")
    private String changedBy;

    @Column("change_reason")
    private String changeReason;

    @Column("comments")
    private String comments;

    @Column("changed_at")
    private LocalDateTime changedAt;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
