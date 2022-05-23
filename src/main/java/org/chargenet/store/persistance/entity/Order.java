package org.chargenet.store.persistance.entity;

import lombok.Data;
import org.chargenet.store.constant.OrderType;
import org.chargenet.store.constant.Status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "order_detail")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "order_type")
    private OrderType type;
    @Column(name = "created_on")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdOn;
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }
}
