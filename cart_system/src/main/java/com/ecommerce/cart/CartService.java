package com.ecommerce.cart;

import java.util.List;

public interface CartService {
    List<Cart> getCartByUser(Long userId);
    Cart addToCart(CartDTO cartDTO);
    Cart updateCart(Long cartId, Integer quantity);
    void removeFromCart(Long cartId);
}
