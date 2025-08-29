package com.firefly.core.lending.mortgages.web.controllers.insurance.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.insurance.v1.MortgageInsuranceService;
import com.firefly.core.lending.mortgages.interfaces.dtos.insurance.v1.MortgageInsuranceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mortgage-contracts/{mortgageContractId}/insurance")
@Tag(name = "MortgageInsurance", description = "Insurance policies associated with a mortgage contract")
@RequiredArgsConstructor
public class MortgageInsuranceController {

    private final MortgageInsuranceService service;

    @GetMapping
    @Operation(summary = "List or search insurance policies for a mortgage contract")
    public Mono<ResponseEntity<PaginationResponse<MortgageInsuranceDTO>>> findAll(
            @PathVariable Long mortgageContractId,
            @ModelAttribute FilterRequest<MortgageInsuranceDTO> filterRequest) {
        return service.findAll(mortgageContractId, filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new mortgage insurance record")
    public Mono<ResponseEntity<MortgageInsuranceDTO>> create(
            @PathVariable Long mortgageContractId,
            @RequestBody MortgageInsuranceDTO dto) {
        return service.create(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{insuranceId}")
    @Operation(summary = "Get an insurance record by ID")
    public Mono<ResponseEntity<MortgageInsuranceDTO>> getById(
            @PathVariable Long mortgageContractId,
            @PathVariable Long insuranceId) {
        return service.getById(mortgageContractId, insuranceId).map(ResponseEntity::ok);
    }

    @PutMapping("/{insuranceId}")
    @Operation(summary = "Update an insurance record")
    public Mono<ResponseEntity<MortgageInsuranceDTO>> update(
            @PathVariable Long mortgageContractId,
            @PathVariable Long insuranceId,
            @RequestBody MortgageInsuranceDTO dto) {
        return service.update(mortgageContractId, insuranceId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{insuranceId}")
    @Operation(summary = "Delete an insurance record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long mortgageContractId,
            @PathVariable Long insuranceId) {
        return service.delete(mortgageContractId, insuranceId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
