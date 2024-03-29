package com.skillstorm.inventorymanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;
    @NotBlank(message = "Category must have a name")
    @Column(unique = true)
    private String name;

}
