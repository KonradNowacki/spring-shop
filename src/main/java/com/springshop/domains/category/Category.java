package com.springshop.domains.category;

import com.springshop.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "categories")
@AllArgsConstructor
public final class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;
}
