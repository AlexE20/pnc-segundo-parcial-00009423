package com.uca.pncsegundoparcialveterinaria.entities;

import com.uca.pncsegundoparcialveterinaria.utils.Categories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="category")
    private Categories category;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="stock")
    private Integer stock;

    @Column(name="available")
    private Boolean available;

    @Column(name="requiresPrescription")
    private Boolean requiresPrescription;

    @Column(name="expirationDate")
    private Date expirationDate;

    @Column(name="supplier")
    private String supplier;
}
