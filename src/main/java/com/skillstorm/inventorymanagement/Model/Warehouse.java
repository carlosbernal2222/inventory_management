package com.skillstorm.inventorymanagement.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.skillstorm.inventorymanagement.Validation.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    private Company company;

    @ManyToOne
    @JoinColumn (name = "company_id")
    private Company company;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Address
    @Column
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "sourceWarehouse", cascade = CascadeType.ALL)
    private List<Transfer> transfersSent;

    @JsonIgnore
    @OneToMany(mappedBy = "destinationWarehouse", cascade = CascadeType.ALL)
    private List<Transfer> transfersReceived;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Inventory> inventory;

    @Column
    @Min(0)
    private int capacity;

    @Column(name = "environment_control")
    private boolean environmentControl;


}

