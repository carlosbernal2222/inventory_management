package com.skillstorm.inventorymanagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String industry;
    @Column
    private String description;

    @OneToMany(targetEntity = Warehouse.class, mappedBy = "company", cascade = CascadeType.ALL)
    private List<Warehouse> warehouses;


}
