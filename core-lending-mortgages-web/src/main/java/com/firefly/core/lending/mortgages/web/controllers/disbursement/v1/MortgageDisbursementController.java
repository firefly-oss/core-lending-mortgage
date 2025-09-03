package com.firefly.core.lending.mortgages.web.controllers.disbursement.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.disbursement.v1.MortgageDisbursementService;
import com.firefly.core.lending.mortgages.interfaces.dtos.disbursement.v1.MortgageDisbursementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import jakarta.validation.Valid;@RestController
@RequestMapping("/api/v1/mortgage-contracts/{mortgageContractId}/disbursements")
@Tag(name = "MortgageDisbursement", description = "Record of funds disbursed under a mortgage contract")
@RequiredArgsConstructor
public class MortgageDisbursementController {

    private final MortgageDisbursementService service;

    @GetMapping
    @Operation(summary = "List or search mortgage disbursements for a contract")
    public Mono<ResponseEntity<PaginationResponse<MortgageDisbursementDTO>>> findAll(
            @PathVariable UUID mortgageContractId,
            @ModelAttribute FilterRequest<MortgageDisbursementDTO> filterRequest) {
        return service.findAll(mortgageContractId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new disbursement record")
    public Mono<ResponseEntity<MortgageDisbursementDTO>> create(
            @PathVariable UUID mortgageContractId,
            @Valid @RequestBody MortgageDisbursementDTO dto) {
        return service.create(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{mortgageDisbursementId}")
    @Operation(summary = "Get a disbursement record by ID")
    public Mono<ResponseEntity<MortgageDisbursementDTO>> getById(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID mortgageDisbursementId) {
        return service.getById(mortgageContractId, mortgageDisbursementId).map(ResponseEntity::ok);
    }

    @PutMapping("/{mortgageDisbursementId}")
    @Operation(summary = "Update a disbursement record")
    public Mono<ResponseEntity<MortgageDisbursementDTO>> update(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID mortgageDisbursementId,
            @Valid @RequestBody MortgageDisbursementDTO dto) {
        return service.update(mortgageContractId, mortgageDisbursementId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{mortgageDisbursementId}")
    @Operation(summary = "Delete a disbursement record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID mortgageContractId,
            @PathVariable UUID mortgageDisbursementId) {
        return service.delete(mortgageContractId, mortgageDisbursementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
