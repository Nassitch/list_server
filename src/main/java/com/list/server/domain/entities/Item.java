package com.list.server.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private short quantity;
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("category")
    private Category category;

}
