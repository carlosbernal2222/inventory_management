package com.skillstorm.inventorymanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationDto {
    private  String code;
    private String name;
    private String description;
    private String subCategory;
    private boolean refrigerated;
    private double weight;
    private long categoryId;
}
