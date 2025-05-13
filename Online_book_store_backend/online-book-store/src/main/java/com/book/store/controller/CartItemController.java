package com.book.store.controller;

import com.book.store.entity.CartItem;
import com.book.store.entity.User;
import com.book.store.entity.Books;
import com.book.store.service.CartItemService;
import com.book.store.repository.UserRepository;
import com.book.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam Integer userId, @RequestParam Integer bookId, @RequestParam int quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Books book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        return cartItemService.addToCart(user, book, quantity);
    }

    @GetMapping("/items")
    public List<CartItem> getCartItems(@RequestParam Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartItemService.getUserCartItems(user);
    }

    @DeleteMapping("/remove")
    public void removeFromCart(@RequestParam Integer userId, @RequestParam Long cartItemId) {
        cartItemService.removeFromCart(null, cartItemId);
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestParam Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        cartItemService.clearCart(user);
    }
}
