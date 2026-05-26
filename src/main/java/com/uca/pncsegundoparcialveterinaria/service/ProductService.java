package com.uca.pncsegundoparcialveterinaria.service;

import com.uca.pncsegundoparcialveterinaria.dto.request.ProductDtoRequest;
import com.uca.pncsegundoparcialveterinaria.dto.response.ProductDtoResponse;

import java.util.List;

public interface ProductService {
    ProductDtoResponse createProduct(ProductDtoRequest request);
    List<ProductDtoResponse> getAllProducts(String category, Boolean available);
    ProductDtoResponse getProductById(Long id);
    ProductDtoResponse updateProduct(Long id, ProductDtoRequest request);
    ProductDtoResponse decreaseStock(Long id, Integer quantity);
    void deleteProduct(Long id);
}