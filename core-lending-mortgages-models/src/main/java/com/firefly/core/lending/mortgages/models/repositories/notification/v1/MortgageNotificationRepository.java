package com.firefly.core.lending.mortgages.models.repositories.notification.v1;

import com.firefly.core.lending.mortgages.models.entities.notification.v1.MortgageNotification;
import com.firefly.core.lending.mortgages.models.repositories.BaseRepository;

import java.util.UUID;
public interface MortgageNotificationRepository extends BaseRepository<MortgageNotification, UUID> {
}
