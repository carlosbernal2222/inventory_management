package com.skillstorm.inventorymanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    @NotBlank(message = "Category must have a name")
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(targetEntity = Product.class, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> productList;

}
