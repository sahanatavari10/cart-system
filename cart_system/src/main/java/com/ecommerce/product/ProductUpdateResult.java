package com.ecommerce.product;

public sealed interface ProductUpdateResult {

    public record IsSuccess(Product product) implements ProductUpdateResult{
    }

    public record NotFound() implements ProductUpdateResult{
    }
}
