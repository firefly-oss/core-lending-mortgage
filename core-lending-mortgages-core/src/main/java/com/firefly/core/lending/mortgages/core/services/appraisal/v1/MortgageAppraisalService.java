package com.firefly.core.lending.mortgages.core.services.appraisal.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.interfaces.dtos.appraisal.v1.MortgageAppraisalDTO;
import reactor.core.publisher.Mono;

public interface MortgageAppraisalService {

    /**
     * Retrieves a paginated list of mortgage appraisals associated with a specific mortgage application,
     * based on the provided filter criteria.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application whose appraisals are to be retrieved
     * @param filterRequest the filter criteria and pagination details for retrieving mortgage appraisals
     * @return a reactive wrapper containing a paginated response of MortgageAppraisalDTOs
     */
    Mono<PaginationResponse<MortgageAppraisalDTO>> findAll(Long mortgageApplicationId,
                                                           FilterRequest<MortgageAppraisalDTO> filterRequest);

    /**
     * Creates a new mortgage appraisal associated with the specified mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application the appraisal is linked to
     * @param dto the data transfer object containing details of the mortgage appraisal to be created
     * @return a Mono emitting the created MortgageAppraisalDTO instance upon successful creation
     */
    Mono<MortgageAppraisalDTO> create(Long mortgageApplicationId, MortgageAppraisalDTO dto);

    /**
     * Retrieves a specific mortgage appraisal associated with a given mortgage application and appraisal ID.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the appraisal belongs
     * @param appraisalId the unique identifier of the appraisal to be retrieved
     * @return a Mono emitting the MortgageAppraisalDTO corresponding to the specified IDs, or an empty Mono if no appraisal is found
     */
    Mono<MortgageAppraisalDTO> getById(Long mortgageApplicationId, Long appraisalId);

    /**
     * Updates an existing mortgage appraisal associated with a specific mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the appraisal belongs
     * @param appraisalId the unique identifier of the appraisal to be updated
     * @param dto the data transfer object containing the updated appraisal details
     * @return a Mono emitting the updated MortgageAppraisalDTO upon successful update,
     *         or an error if the update operation fails
     */
    Mono<MortgageAppraisalDTO> update(Long mortgageApplicationId, Long appraisalId, MortgageAppraisalDTO dto);

    /**
     * Deletes a specific appraisal associated with a mortgage application.
     *
     * @param mortgageApplicationId the unique identifier of the mortgage application to which the appraisal belongs
     * @param appraisalId the unique identifier of the appraisal to be deleted
     * @return a {@code Mono<Void>} indicating the completion of the delete operation
     */
    Mono<Void> delete(Long mortgageApplicationId, Long appraisalId);
}
