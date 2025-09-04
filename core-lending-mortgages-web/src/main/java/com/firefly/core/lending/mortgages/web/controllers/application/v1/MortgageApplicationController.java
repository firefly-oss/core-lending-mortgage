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


package com.firefly.core.lending.mortgages.web.controllers.application.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.mortgages.core.services.application.v1.MortgageApplicationService;
import com.firefly.core.lending.mortgages.interfaces.dtos.application.v1.MortgageApplicationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/mortgage-applications")
@Tag(name = "MortgageApplication", description = "Operations on mortgage applications")
@RequiredArgsConstructor
public class MortgageApplicationController {

    private final MortgageApplicationService service;

    @GetMapping
    @Operation(summary = "List or search mortgage applications")
    public Mono<ResponseEntity<PaginationResponse<MortgageApplicationDTO>>> findAll(
            @ModelAttribute FilterRequest<MortgageApplicationDTO> filterRequest) {
        return service.findAll(filterRequest).map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new mortgage application")
    public Mono<ResponseEntity<MortgageApplicationDTO>> create(
            @Valid @RequestBody MortgageApplicationDTO dto) {
        return service.create(dto).map(ResponseEntity::ok);
    }

    @GetMapping("/{mortgageApplicationId}")
    @Operation(summary = "Get a mortgage application by ID")
    public Mono<ResponseEntity<MortgageApplicationDTO>> getById(
            @PathVariable UUID mortgageApplicationId) {
        return service.getById(mortgageApplicationId).map(ResponseEntity::ok);
    }

    @PutMapping("/{mortgageApplicationId}")
    @Operation(summary = "Update a mortgage application")
    public Mono<ResponseEntity<MortgageApplicationDTO>> update(
            @PathVariable UUID mortgageApplicationId,
            @Valid @RequestBody MortgageApplicationDTO dto) {
        return service.update(mortgageApplicationId, dto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{mortgageApplicationId}")
    @Operation(summary = "Delete a mortgage application")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID mortgageApplicationId) {
        return service.delete(mortgageApplicationId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
