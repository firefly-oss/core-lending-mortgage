package com.firefly.core.lending.mortgages.models.repositories.application.v1;

import com.firefly.core.lending.mortgages.models.entities.application.v1.MortgageApplication;
import com.firefly.core.lending.mortgages.models.repositories.BaseRepository;

import java.util.UUID;

public interface MortgageApplicationRepository extends BaseRepository<MortgageApplication, UUID> {
}