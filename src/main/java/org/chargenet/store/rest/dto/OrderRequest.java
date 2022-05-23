package org.chargenet.store.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.chargenet.store.constant.OrderType;
import org.chargenet.store.constant.Status;
import org.chargenet.store.model.OrderItem;

import java.util.List;

@Setter
@Getter
public class OrderRequest {

    private OrderType type;
    private Status status;
    private List<OrderItem> items;
}
