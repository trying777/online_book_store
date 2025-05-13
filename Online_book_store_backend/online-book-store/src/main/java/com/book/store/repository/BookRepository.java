package com.book.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.store.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {

}
