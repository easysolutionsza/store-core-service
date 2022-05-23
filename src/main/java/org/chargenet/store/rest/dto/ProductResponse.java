package org.chargenet.store.rest.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private boolean isActive;
}
