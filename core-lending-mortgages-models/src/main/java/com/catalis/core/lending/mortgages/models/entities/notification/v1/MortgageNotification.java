package com.catalis.core.lending.mortgages.models.entities.notification.v1;

import com.catalis.core.lending.mortgages.interfaces.enums.notification.v1.NotificationTypeEnum;
import com.catalis.core.lending.mortgages.interfaces.enums.notification.v1.PriorityEnum;
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
@Table("mortgage_notification")
public class MortgageNotification {

    @Id
    @Column("notification_id")
    private Long notificationId;

    @Column("mortgage_contract_id")
    private Long mortgageContractId;

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