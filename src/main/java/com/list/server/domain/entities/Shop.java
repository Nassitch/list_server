package com.list.server.domain.entities;

import com.list.server.domain.enums.StatusShop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_shop", nullable = false)
    private StatusShop status = StatusShop.OPENED;
    @Column(name = "item_id", nullable = false)
    private Long itemId;
}
