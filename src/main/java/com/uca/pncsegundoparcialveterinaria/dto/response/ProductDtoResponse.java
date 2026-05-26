package com.uca.pncsegundoparcialveterinaria.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoResponse {
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private Integer stock;
    private Boolean available;
    private Boolean requiresPrescription;
    private LocalDate expirationDate;
    private String supplier;
}
