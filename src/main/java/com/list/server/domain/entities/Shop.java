package com.list.server.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "item_id")
//    @JsonIgnoreProperties("shops")
    @JsonIgnore
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
