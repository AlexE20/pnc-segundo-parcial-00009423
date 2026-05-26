package com.uca.pncsegundoparcialveterinaria.service.impl;

import com.uca.pncsegundoparcialveterinaria.dto.request.ProductDtoRequest;
import com.uca.pncsegundoparcialveterinaria.dto.response.ProductDtoResponse;
import com.uca.pncsegundoparcialveterinaria.entities.Product;
import com.uca.pncsegundoparcialveterinaria.exception.BusinessRuleException;
import com.uca.pncsegundoparcialveterinaria.exception.ProductNotFound;
import com.uca.pncsegundoparcialveterinaria.utils.ProductMapper;
import com.uca.pncsegundoparcialveterinaria.repository.ProductRepository;
import com.uca.pncsegundoparcialveterinaria.service.ProductService;
import com.uca.pncsegundoparcialveterinaria.utils.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDtoResponse createProduct(ProductDtoRequest request) {

        boolean exists = productRepository.findAll()
                .stream()
                .anyMatch(product ->
                        product.getName().equalsIgnoreCase(request.getName())
                );

        if (exists) {
            throw new BusinessRuleException("A product with this name already exists.");
        }

        Product product = productMapper.toEntity(request);

        product.setAvailable(product.getStock() > 0);

        product.setRequiresPrescription(
                product.getCategory() == Categories.MEDICINE ||
                        product.getCategory() == Categories.VACCINE
        );

        Product savedProduct = productRepository.save(product);

        return productMapper.toDto(savedProduct);
    }

    @Override
    public List<ProductDtoResponse> getAllProducts(String category, Boolean available) {

        List<Product> products = productRepository.findAll();

        if (category != null) {
            products = products.stream()
                    .filter(product ->
                            product.getCategory().name().equalsIgnoreCase(category)
                    )
                    .collect(Collectors.toList());
        }

        if (available != null) {
            products = products.stream()
                    .filter(product ->
                            product.getAvailable().equals(available)
                    )
                    .collect(Collectors.toList());
        }

        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDtoResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFound("Product not found.")
                );

        return productMapper.toDto(product);
    }

    @Override
    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest request) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFound("Product not found.")
                );

        boolean exists = productRepository.findAll()
                .stream()
                .anyMatch(product ->
                        !product.getId().equals(id) &&
                                product.getName().equalsIgnoreCase(request.getName())
                );

        if (exists) {
            throw new BusinessRuleException("A product with this name already exists.");
        }

        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setCategory(
                Categories.valueOf(request.getCategory().toUpperCase())
        );
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStock(request.getStock());
        existingProduct.setExpirationDate(
                java.sql.Date.valueOf(request.getExpirationDate())
        );
        existingProduct.setSupplier(request.getSupplier());

        existingProduct.setAvailable(existingProduct.getStock() > 0);

        existingProduct.setRequiresPrescription(
                existingProduct.getCategory() == Categories.MEDICINE ||
                        existingProduct.getCategory() == Categories.VACCINE
        );

        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toDto(updatedProduct);
    }

    @Override
    public ProductDtoResponse decreaseStock(Long id, Integer quantity) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFound("Product not found.")
                );

        int newStock = product.getStock() - quantity;

        if (newStock < 0) {
            throw new BusinessRuleException("Stock cannot be negative.");
        }

        product.setStock(newStock);

        if (newStock == 0) {
            product.setAvailable(false);
        }

        Product updatedProduct = productRepository.save(product);

        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFound("Product not found.")
                );

        if (
                product.getCategory() == Categories.VACCINE &&
                        product.getAvailable()
        ) {
            throw new BusinessRuleException(
                    "Available vaccines cannot be deleted."
            );
        }

        productRepository.deleteById(id);
    }
}