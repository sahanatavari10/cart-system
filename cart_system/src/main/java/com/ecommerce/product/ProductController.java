package com.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/e-commerce/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductGetResult> getProductById(@PathVariable Long productId){
        var result = productService.getProductById(productId);
        return switch (result){
            case ProductGetResult.IsSuccess success -> ResponseEntity.ok(success);
            case ProductGetResult.NotFound notFound -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        };
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDTO));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductUpdateResult> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        var result = productService.updateProduct(productId, productDTO);
        return switch (result){
            case ProductUpdateResult.IsSuccess isSuccess -> ResponseEntity.ok(isSuccess);
            case ProductUpdateResult.NotFound notFound -> ResponseEntity.notFound().build();
        };
    }

    @DeleteMapping("/productId")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        log.info("Deleted product with product_id {}", productId);
        return ResponseEntity.noContent().build();
    }

}
