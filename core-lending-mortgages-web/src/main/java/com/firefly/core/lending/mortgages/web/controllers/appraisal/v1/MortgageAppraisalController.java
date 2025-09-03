package com.firefly.core.lending.mortgages.web.controllers.appraisal.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.appraisal.v1.MortgageAppraisalService;
import com.firefly.core.lending.mortgages.interfaces.dtos.appraisal.v1.MortgageAppraisalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import jakarta.validation.Valid;@RestController
@RequestMapping("/api/v1/mortgage-applications/{mortgageApplicationId}/appraisals")
@Tag(name = "MortgageAppraisal", description = "Appraisal records for a mortgage application")
@RequiredArgsConstructor
public class MortgageAppraisalController {

    private final MortgageAppraisalService service;

    @GetMapping
    @Operation(summary = "List or search appraisals for a mortgage application")
    public Mono<ResponseEntity<PaginationResponse<MortgageAppraisalDTO>>> findAll(
            @PathVariable UUID mortgageApplicationId,
            @ModelAttribute FilterRequest<MortgageAppraisalDTO> filterRequest) {
        return service.findAll(mortgageApplicationId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new appraisal record")
    public Mono<ResponseEntity<MortgageAppraisalDTO>> create(
            @PathVariable UUID mortgageApplicationId,
            @Valid @RequestBody MortgageAppraisalDTO dto) {
        return service.create(mortgageApplicationId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{appraisalId}")
    @Operation(summary = "Get an appraisal record by ID")
    public Mono<ResponseEntity<MortgageAppraisalDTO>> getById(
            @PathVariable UUID mortgageApplicationId,
            @PathVariable UUID appraisalId) {
        return service.getById(mortgageApplicationId, appraisalId).map(ResponseEntity::ok);
    }

    @PutMapping("/{appraisalId}")
    @Operation(summary = "Update an appraisal record")
    public Mono<ResponseEntity<MortgageAppraisalDTO>> update(
            @PathVariable UUID mortgageApplicationId,
            @PathVariable UUID appraisalId,
            @Valid @RequestBody MortgageAppraisalDTO dto) {
        return service.update(mortgageApplicationId, appraisalId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{appraisalId}")
    @Operation(summary = "Delete an appraisal record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID mortgageApplicationId,
            @PathVariable UUID appraisalId) {
        return service.delete(mortgageApplicationId, appraisalId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
