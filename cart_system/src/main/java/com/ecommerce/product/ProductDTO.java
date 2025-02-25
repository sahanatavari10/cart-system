package com.ecommerce.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
