package com.firefly.core.lending.mortgages.models.repositories.schedule.v1;

import com.firefly.core.lending.mortgages.models.entities.schedule.v1.MortgagePaymentSchedule;
import com.firefly.core.lending.mortgages.models.repositories.BaseRepository;

import java.util.UUID;
public interface MortgagePaymentScheduleRepository extends BaseRepository<MortgagePaymentSchedule, UUID> {
}
