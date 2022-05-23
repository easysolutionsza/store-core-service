package org.chargenet.store.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item_detail")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double prise;
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
