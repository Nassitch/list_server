package com.list.server.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private boolean isCompleted = false;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Item> items;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.REMOVE)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
