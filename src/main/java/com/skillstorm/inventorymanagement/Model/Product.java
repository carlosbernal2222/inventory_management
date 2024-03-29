package com.skillstorm.inventorymanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    private String code;

    @NotBlank(message = "Product must have a Name")
    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String type;

    @Column
    private boolean refrigerated;

    @Column
    private double weight;

}
