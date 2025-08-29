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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("mortgage_application_status_history")
public class MortgageApplicationStatusHistory {

    @Id
    @Column("status_history_id")
    private Long statusHistoryId;

    @Column("mortgage_application_id")
    private Long mortgageApplicationId;

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
