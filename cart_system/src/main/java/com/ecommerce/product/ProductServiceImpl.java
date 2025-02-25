package com.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
@Validated
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public ProductGetResult getProductById(Long id){
        var maybeProduct =  productRepository.findByProductId(id);
        if (maybeProduct.isEmpty()) {
            return new ProductGetResult.NotFound();
        }
        return new ProductGetResult.IsSuccess(maybeProduct.get());
    }

    @Override
    public Product addProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        return productRepository.save(product);
    }

    @Override
    public ProductUpdateResult updateProduct(Long id, ProductDTO productDTO){
        var maybeProduct =  productRepository.findByProductId(id);
        if (maybeProduct.isEmpty()) {
            return new ProductUpdateResult.NotFound();
        }
        var product = maybeProduct.get();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setLastUpdatedAt(ZonedDateTime.now());
        return new ProductUpdateResult.IsSuccess(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteByProductId(id);
    }
}
