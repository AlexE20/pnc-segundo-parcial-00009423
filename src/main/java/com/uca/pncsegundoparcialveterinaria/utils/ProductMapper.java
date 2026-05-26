package com.uca.pncsegundoparcialveterinaria.utils;


import com.uca.pncsegundoparcialveterinaria.dto.request.ProductDtoRequest;
import com.uca.pncsegundoparcialveterinaria.dto.response.ProductDtoResponse;
import com.uca.pncsegundoparcialveterinaria.entities.Product;
import com.uca.pncsegundoparcialveterinaria.utils.Categories;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductDtoRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(Categories.valueOf(request.getCategory().toUpperCase()))
                .price(request.getPrice())
                .stock(request.getStock())
                .available(request.getAvailable())
                .requiresPrescription(request.getRequiresPrescription())
                .expirationDate(java.sql.Date.valueOf(request.getExpirationDate()))
                .supplier(request.getSupplier())
                .build();
    }

    public ProductDtoResponse toDto(Product product) {
        return ProductDtoResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory().name())
                .price(product.getPrice())
                .stock(product.getStock())
                .available(product.getAvailable())
                .requiresPrescription(product.getRequiresPrescription())
                .expirationDate(
                        product.getExpirationDate()
                                .toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDate()
                )
                .supplier(product.getSupplier())
                .build();
    }
}
