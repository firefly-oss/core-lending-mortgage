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


package com.firefly.core.lending.mortgages.web.controllers.property.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.property.v1.MortgagePropertyService;
import com.firefly.core.lending.mortgages.interfaces.dtos.property.v1.MortgagePropertyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import jakarta.validation.Valid;@RestController
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
            @Valid @RequestBody MortgagePropertyDTO dto) {
        return service.create(dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{propertyId}")
    @Operation(summary = "Get a property record by ID")
    public Mono<ResponseEntity<MortgagePropertyDTO>> getById(
            @PathVariable UUID propertyId) {
        return service.getById(propertyId).map(ResponseEntity::ok);
    }

    @PutMapping("/{propertyId}")
    @Operation(summary = "Update a property record")
    public Mono<ResponseEntity<MortgagePropertyDTO>> update(
            @PathVariable UUID propertyId,
            @Valid @RequestBody MortgagePropertyDTO dto) {
        return service.update(propertyId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{propertyId}")
    @Operation(summary = "Delete a property record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID propertyId) {
        return service.delete(propertyId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
