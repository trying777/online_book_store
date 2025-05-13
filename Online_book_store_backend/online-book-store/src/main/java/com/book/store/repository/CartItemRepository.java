package com.book.store.repository;

import com.book.store.entity.CartItem;
import com.book.store.entity.User;
import com.book.store.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public List<CartItem> findByUser(User user);
    public Optional<CartItem> findByUserAndBook(User user, Books book);
}
