package com.springshop.domains.category;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "categories")
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;
}
