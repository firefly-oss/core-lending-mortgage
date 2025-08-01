package com.catalis.core.lending.mortgages.web.controllers.property.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.mortgages.core.services.property.v1.MortgagePropertyService;
import com.catalis.core.lending.mortgages.interfaces.dtos.property.v1.MortgagePropertyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mortgage-properties")
@Tag(name = "MortgageProperty", description = "Management of properties used as collateral for mortgages")
@RequiredArgsConstructor
public class MortgagePropertyController {

    private final MortgagePropertyService service;

    @GetMapping
    @Operation(summary = "List or search mortgage properties")
    public Mono<ResponseEntity<PaginationResponse<MortgagePropertyDTO>>> findAll(
            @ModelAttribute FilterRequest<MortgagePropertyDTO> filterRequest) {
        return service.findAll(filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new property record")
    public Mono<ResponseEntity<MortgagePropertyDTO>> create(
            @RequestBody MortgagePropertyDTO dto) {
        return service.create(dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{propertyId}")
    @Operation(summary = "Get a property record by ID")
    public Mono<ResponseEntity<MortgagePropertyDTO>> getById(
            @PathVariable Long propertyId) {
        return service.getById(propertyId).map(ResponseEntity::ok);
    }

    @PutMapping("/{propertyId}")
    @Operation(summary = "Update a property record")
    public Mono<ResponseEntity<MortgagePropertyDTO>> update(
            @PathVariable Long propertyId,
            @RequestBody MortgagePropertyDTO dto) {
        return service.update(propertyId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{propertyId}")
    @Operation(summary = "Delete a property record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long propertyId) {
        return service.delete(propertyId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
