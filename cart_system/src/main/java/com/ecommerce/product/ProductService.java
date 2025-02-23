package com.ecommerce.product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product addProduct(ProductDTO product);
    Product updateProduct(Long productId,ProductDTO product);
    void deleteProduct(Long productId);
}
