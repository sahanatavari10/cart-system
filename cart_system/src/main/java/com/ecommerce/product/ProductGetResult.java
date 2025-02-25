package com.ecommerce.product;

public sealed interface ProductGetResult {

    record IsSuccess(Product product) implements ProductGetResult{
    }

    record NotFound() implements ProductGetResult{

    }
}