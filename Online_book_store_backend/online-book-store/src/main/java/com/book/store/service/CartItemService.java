package com.book.store.service;

import com.book.store.entity.CartItem;
import com.book.store.entity.User;
import com.book.store.entity.Books;

import java.util.List;

public interface CartItemService {
    public CartItem addToCart(User user, Books book, int quantity);
    public void removeFromCart(User user, Long cartItemId);
    public List<CartItem> getUserCartItems(User user);
    public void clearCart(User user);
}
