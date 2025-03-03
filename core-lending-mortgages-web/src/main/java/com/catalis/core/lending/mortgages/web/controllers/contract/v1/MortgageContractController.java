package com.catalis.core.lending.mortgages.web.controllers.contract.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.core.services.contract.v1.MortgageContractService;
import com.catalis.core.lending.mortgages.interfaces.dtos.contract.v1.MortgageContractDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mortgage-contracts")
@Tag(name = "MortgageContract", description = "Operations on final mortgage contracts")
@RequiredArgsConstructor
public class MortgageContractController {

    private final MortgageContractService service;

    @GetMapping
    @Operation(summary = "List or search mortgage contracts")
    public Mono<ResponseEntity<PaginationResponse<MortgageContractDTO>>> findAll(
            @ModelAttribute FilterRequest<MortgageContractDTO> filterRequest) {
        return service.findAll(filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new mortgage contract")
    public Mono<ResponseEntity<MortgageContractDTO>> create(
            @RequestBody MortgageContractDTO dto) {
        return service.create(dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{mortgageContractId}")
    @Operation(summary = "Get a mortgage contract by ID")
    public Mono<ResponseEntity<MortgageContractDTO>> getById(
            @PathVariable Long mortgageContractId) {
        return service.getById(mortgageContractId).map(ResponseEntity::ok);
    }

    @PutMapping("/{mortgageContractId}")
    @Operation(summary = "Update a mortgage contract")
    public Mono<ResponseEntity<MortgageContractDTO>> update(
            @PathVariable Long mortgageContractId,
            @RequestBody MortgageContractDTO dto) {
        return service.update(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{mortgageContractId}")
    @Operation(summary = "Delete a mortgage contract")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long mortgageContractId) {
        return service.delete(mortgageContractId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
