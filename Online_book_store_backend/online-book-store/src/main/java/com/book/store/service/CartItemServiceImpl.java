package com.book.store.service;

import com.book.store.entity.CartItem;
import com.book.store.entity.User;
import com.book.store.entity.Books;
import com.book.store.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addToCart(User user, Books book, int quantity) {
        Optional<CartItem> existingItem = cartItemRepository.findByUserAndBook(user, book);
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            return cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setBook(book);
            newItem.setQuantity(quantity);
            return cartItemRepository.save(newItem);
        }
    }

    @Override
    public void removeFromCart(User user, Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<CartItem> getUserCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    public void clearCart(User user) {
        List<CartItem> items = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAll(items);
    }
}
