package com.skillstorm.inventorymanagement.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column
    private String address;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

}
