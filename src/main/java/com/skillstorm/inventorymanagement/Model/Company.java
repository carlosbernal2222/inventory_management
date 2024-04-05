package com.skillstorm.inventorymanagement.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.skillstorm.inventorymanagement.Validation.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    @Address
    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column
    private String industry;

    @Column
    private String description;

    @OneToMany(targetEntity = Warehouse.class, mappedBy = "company", cascade = CascadeType.ALL)
    private List<Warehouse> warehouses;


}
