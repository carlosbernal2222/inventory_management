package com.skillstorm.inventorymanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseUpdateDto {

    private String name;
    private String address;
    private int capacity;
    private Long categoryId;
    private Long companyId;
    private boolean environmentControl;


}
