package com.ecommerce.cart;

import com.ecommerce.product.Product;
import com.ecommerce.product.ProductRepository;
import com.ecommerce.user.User;
import com.ecommerce.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Cart addToCart(Long userId, Long productId, Integer quantity){
        Product product = productRepository.findByProductId(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Cart cart = Cart.builder()
                .user(user)
                .product(product)
                .quantity(quantity)
                .addedAt(LocalDateTime.now())
                .build();
        return cartRepository.save(cart);
    }
}
