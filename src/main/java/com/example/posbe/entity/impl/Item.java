package com.example.posbe.entity.impl;

import com.example.posbe.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item implements SuperEntity {
    @Id
    @Column(name = "item_code")
    private String itemCode;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int qty;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
