package com.springshop.domains.category;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "category")
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
