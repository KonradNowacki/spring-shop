package com.springshop.domains.product;

import com.springshop.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "products")
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;
}
