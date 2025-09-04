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


package com.firefly.core.lending.mortgages.web.controllers.contract.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.contract.v1.MortgageContractService;
import com.firefly.core.lending.mortgages.interfaces.dtos.contract.v1.MortgageContractDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import jakarta.validation.Valid;@RestController
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
            @Valid @RequestBody MortgageContractDTO dto) {
        return service.create(dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{mortgageContractId}")
    @Operation(summary = "Get a mortgage contract by ID")
    public Mono<ResponseEntity<MortgageContractDTO>> getById(
            @PathVariable UUID mortgageContractId) {
        return service.getById(mortgageContractId).map(ResponseEntity::ok);
    }

    @PutMapping("/{mortgageContractId}")
    @Operation(summary = "Update a mortgage contract")
    public Mono<ResponseEntity<MortgageContractDTO>> update(
            @PathVariable UUID mortgageContractId,
            @Valid @RequestBody MortgageContractDTO dto) {
        return service.update(mortgageContractId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{mortgageContractId}")
    @Operation(summary = "Delete a mortgage contract")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID mortgageContractId) {
        return service.delete(mortgageContractId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
