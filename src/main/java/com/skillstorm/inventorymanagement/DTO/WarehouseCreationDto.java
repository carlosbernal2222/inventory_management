package com.skillstorm.inventorymanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseCreationDto {

    private String name;
    private String address;
    private int capacity;
    private boolean environmentControl;
    private Long companyId;
    private Long categoryId;


}
