package com.springshop.domains.product;

import com.springshop.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product_category")
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productCategoryId;

    @Column(name = "name")
    private String name;
}
