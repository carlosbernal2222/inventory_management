package com.skillstorm.inventorymanagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private long id;
    @Column
    private int quantity;
    @Column
    private Date sentDate;
    @Column
    private Date receivedDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "source_warehouse_id", referencedColumnName = "warehouse_id") // Different column name for source warehouse
    private Warehouse sourceWarehouse;

    @ManyToOne
    @JoinColumn(name = "destination_warehouse_id", referencedColumnName = "warehouse_id") // Different column name for destination warehouse
    private Warehouse destinationWarehouse;
}
