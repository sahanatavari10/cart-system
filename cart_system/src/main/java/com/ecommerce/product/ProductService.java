package com.ecommerce.product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    ProductGetResult getProductById(Long productId);
    Product addProduct(ProductDTO product);
    ProductUpdateResult updateProduct(Long productId,ProductDTO product);
    void deleteProduct(Long productId);
}
