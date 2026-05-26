package com.uca.pncsegundoparcialveterinaria.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public class ProductDtoRequest {

    @NotBlank(message = "The name is required.")
    private String name;

    private String description;

    @NotBlank(message = "The category is required.")
    private String category;

    @NotNull(message = "The price is required.")
    @Positive(message = "The price must be greater than 0.")
    private BigDecimal price;

    @NotNull(message = "The stock is required.")
    @Min(value = 0, message = "The stock cannot be negative.")
    private Integer stock;

    private Boolean available;

    private Boolean requiresPrescription;

    @NotNull(message = "The expiration date is required.")
    @Future(message = "The expiration date must be in the future.")
    private LocalDate expirationDate;

    @NotBlank(message = "The supplier is required.")
    private String supplier;
}