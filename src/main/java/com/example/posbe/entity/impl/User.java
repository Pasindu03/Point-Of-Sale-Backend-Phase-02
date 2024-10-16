package com.example.posbe.entity.impl;

import com.example.posbe.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements SuperEntity {
    @Id
    private String userId;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Customer> customers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Item> items;
}
