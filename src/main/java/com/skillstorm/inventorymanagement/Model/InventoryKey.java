package com.skillstorm.inventorymanagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Data
public class InventoryKey implements Serializable {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

}
