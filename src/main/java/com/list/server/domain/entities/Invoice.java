package com.list.server.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
//@JsonIgnoreProperties({"user"})
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date createdAt;
    private short total;

    @ManyToOne
    @JoinColumn(name = "market_id")
    @JsonIgnoreProperties({"invoices", "market"})
    private Market market;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonIgnoreProperties({"invoices", "shops"})
    @JsonIgnoreProperties("user")
    private User user;
}
