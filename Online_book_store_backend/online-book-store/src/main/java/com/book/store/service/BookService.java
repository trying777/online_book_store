package com.book.store.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.book.store.entity.Books;

public interface BookService {

	public Books addBooks(Books books);

	public Books findBookById(Integer bookId);

	public Books updateBookByBookId(Books books);

	public List<Books> getAllBooks();

	public ResponseEntity<String> deleteBookById(Integer bookId);

}
