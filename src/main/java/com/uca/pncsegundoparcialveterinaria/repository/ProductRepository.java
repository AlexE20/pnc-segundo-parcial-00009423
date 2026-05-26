package com.uca.pncsegundoparcialveterinaria.repository;

import com.uca.pncsegundoparcialveterinaria.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
}
