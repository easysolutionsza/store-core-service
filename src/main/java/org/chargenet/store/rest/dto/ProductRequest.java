package org.chargenet.store.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {
    private String name;
    private Double price;
}
