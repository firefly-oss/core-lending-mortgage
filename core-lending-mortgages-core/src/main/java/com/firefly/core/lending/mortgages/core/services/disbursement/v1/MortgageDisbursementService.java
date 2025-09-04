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


package com.firefly.core.lending.mortgages.core.services.disbursement.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.disbursement.v1.MortgageDisbursementDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;
public interface MortgageDisbursementService {

    /**
     * Retrieves a paginated list of mortgage disbursements associated with a specific mortgage contract,
     * based on the provided filter criteria.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract whose disbursements are to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage disbursements
     * @return a reactive wrapper containing a paginated response of MortgageDisbursementDTOs
     */
    Mono<PaginationResponse<MortgageDisbursementDTO>> findAll(UUID mortgageContractId,
                                                              FilterRequest<MortgageDisbursementDTO> filterRequest);

    /**
     * Creates a new mortgage disbursement associated with the specified mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the disbursement belongs
     * @param dto the data transfer object containing the details of the mortgage disbursement to be created
     * @return a Mono emitting the created MortgageDisbursementDTO instance upon successful creation
     */
    Mono<MortgageDisbursementDTO> create(UUID mortgageContractId, MortgageDisbursementDTO dto);

    /**
     * Retrieves a specific mortgage disbursement associated with a given mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the disbursement belongs
     * @param mortgageDisbursementId the unique identifier of the mortgage disbursement to be retrieved
     * @return a Mono emitting the MortgageDisbursementDTO corresponding to the specified IDs,
     *         or an empty Mono if no disbursement is found
     */
    Mono<MortgageDisbursementDTO> getById(UUID mortgageContractId, UUID mortgageDisbursementId);

    /**
     * Updates an existing mortgage disbursement with the provided details.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract associated with the disbursement
     * @param mortgageDisbursementId the unique identifier of the mortgage disbursement to be updated
     * @param dto the data transfer object containing the updated details of the mortgage disbursement
     * @return a Mono emitting the updated MortgageDisbursementDTO upon successful update,
     *         or an error if the update operation fails
     */
    Mono<MortgageDisbursementDTO> update(UUID mortgageContractId, UUID mortgageDisbursementId,
                                         MortgageDisbursementDTO dto);

    /**
     * Deletes a specific mortgage disbursement associated with a given mortgage contract.
     *
     * @param mortgageContractId the unique identifier of the mortgage contract to which the disbursement belongs
     * @param mortgageDisbursementId the unique identifier of the mortgage disbursement to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(UUID mortgageContractId, UUID mortgageDisbursementId);
}
