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


package com.firefly.core.lending.mortgages.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.MortgageAgreementDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;
public interface MortgageAgreementService {

    /**
     * Retrieves a paginated list of mortgage agreements based on the provided filter criteria.
     *
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage agreements
     * @return a reactive wrapper containing a paginated response of MortgageAgreementDTOs
     */
    Mono<PaginationResponse<MortgageAgreementDTO>> findAll(FilterRequest<MortgageAgreementDTO> filterRequest);

    /**
     * Creates a new mortgage agreement based on the provided data.
     *
     * @param dto the data transfer object containing the details of the mortgage agreement to be created
     * @return a Mono emitting the created MortgageAgreementDTO instance upon successful creation
     */
    Mono<MortgageAgreementDTO> create(MortgageAgreementDTO dto);

    /**
     * Retrieves a specific mortgage agreement by its unique identifier.
     *
     * @param mortgageAgreementId the unique identifier of the mortgage agreement to be retrieved
     * @return a Mono emitting the MortgageAgreementDTO corresponding to the specified ID,
     *         or an empty Mono if no agreement is found
     */
    Mono<MortgageAgreementDTO> getById(UUID mortgageAgreementId);

    /**
     * Updates an existing mortgage agreement with the provided details.
     *
     * @param mortgageAgreementId the unique identifier of the mortgage agreement to be updated
     * @param dto the data transfer object containing the updated details of the mortgage agreement
     * @return a Mono emitting the updated MortgageAgreementDTO upon successful update,
     *         or an error if the update operation fails
     */
    Mono<MortgageAgreementDTO> update(UUID mortgageAgreementId, MortgageAgreementDTO dto);

    /**
     * Deletes a mortgage agreement identified by its unique ID.
     *
     * @param mortgageAgreementId the unique identifier of the mortgage agreement to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(UUID mortgageAgreementId);
}
