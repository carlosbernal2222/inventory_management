package com.skillstorm.inventorymanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column
    private String name;

    @Column
    private String address;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Inventory> inventoryList;

    @Column
    @Max(100000)
    @Min(0)
    private int capacity;

    public Warehouse(Company company, String name) {
        this.company = company;
        this.name = name;
        this.capacity = 0;
    }


//    public void addItems(Product item){
//        if(items == null){
//            items = new ArrayList<>();
//        }else{
//            items.add(item);
//        }
//    }
}

