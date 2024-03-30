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

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @OneToMany(mappedBy = "sourceWarehouse", cascade = CascadeType.ALL)
    private List<Transfer> transfersSent;

    @OneToMany(mappedBy = "destinationWarehouse", cascade = CascadeType.ALL)
    private List<Transfer> transfersReceived;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Inventory> inventoryList;

    @Column
    @Max(100000)
    @Min(0)
    private int capacity;

    @Column(name = "environment_control")
    private boolean environmentControl;






//    public void addItems(Product item){
//        if(items == null){
//            items = new ArrayList<>();
//        }else{
//            items.add(item);
//        }
//    }
}

