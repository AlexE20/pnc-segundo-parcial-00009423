package com.uca.pncsegundoparcialveterinaria.exception;



public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message) {
        super(message);
    }
}