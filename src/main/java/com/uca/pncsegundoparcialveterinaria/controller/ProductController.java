package com.uca.pncsegundoparcialveterinaria.controller;

import com.uca.pncsegundoparcialveterinaria.dto.GeneralResponse;
import com.uca.pncsegundoparcialveterinaria.dto.request.ProductDtoRequest;
import com.uca.pncsegundoparcialveterinaria.dto.response.ProductDtoResponse;
import com.uca.pncsegundoparcialveterinaria.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<GeneralResponse> createProduct(
            @Valid @RequestBody ProductDtoRequest request
    ) {

        ProductDtoResponse response = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        GeneralResponse.builder()
                                .data(response)
                                .message("Product created successfully.")
                                .build()
                );
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean available
    ) {

        return ResponseEntity.ok(
                GeneralResponse.builder()
                        .data(productService.getAllProducts(category, available))
                        .message("Products retrieved successfully.")
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getProductById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                GeneralResponse.builder()
                        .data(productService.getProductById(id))
                        .message("Product retrieved successfully.")
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDtoRequest request
    ) {

        return ResponseEntity.ok(
                GeneralResponse.builder()
                        .data(productService.updateProduct(id, request))
                        .message("Product updated successfully.")
                        .build()
        );
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<GeneralResponse> decreaseStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {

        return ResponseEntity.ok(
                GeneralResponse.builder()
                        .data(productService.decreaseStock(id, quantity))
                        .message("Stock updated successfully.")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteProduct(
            @PathVariable Long id
    ) {

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                GeneralResponse.builder()
                        .data(null)
                        .message("Product deleted successfully.")
                        .build()
        );
    }
}