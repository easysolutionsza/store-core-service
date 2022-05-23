package org.chargenet.store.persistance.entity;

import lombok.Data;
import org.chargenet.store.constant.Status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "product_detail")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "created_on")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdOn;
    @Column(name = "is_active")
    private boolean isActive;
}
