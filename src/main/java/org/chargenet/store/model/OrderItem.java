package org.chargenet.store.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItem {

    private long prodId;
    private String name;
    private double prise;
    private int quantity;

}
