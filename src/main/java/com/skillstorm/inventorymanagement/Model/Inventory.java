package com.skillstorm.inventorymanagement.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "quantity_available")
    @Min(0)
    private int quantityAvailable;

    public Inventory() {}

    public Inventory(Warehouse warehouse, Product product, int quantityAvailable) {
        this.warehouse = warehouse;
        this.product = product;
        this.quantityAvailable = quantityAvailable;
    }

}
