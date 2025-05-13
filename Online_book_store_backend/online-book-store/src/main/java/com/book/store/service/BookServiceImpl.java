package com.book.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.store.entity.Books;
import com.book.store.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Books addBooks(Books books) {
		return bookRepository.save(books);
	}

	@Override
	public Books findBookById(Integer bookId) {
		return bookRepository.findById(bookId).orElse(null);
	}

	@Override
	public Books updateBookByBookId(Books books) {
	    Optional<Books> bookExistOpt = bookRepository.findById(books.getBookId());
	    
	    if (bookExistOpt.isEmpty()) {
	        return null;
	    }

	    Books bookExist = bookExistOpt.get();
	    bookExist.setBookTitle(books.getBookTitle());
	    bookExist.setAuthorName(books.getAuthorName());
	    bookExist.setDescription(books.getDescription());
	    bookExist.setGenre(books.getGenre());
	    bookExist.setYop(books.getYop());
	    bookExist.setBookPrice(books.getBookPrice());
	    bookExist.setQuantity(books.getQuantity());

	    return bookRepository.save(bookExist);
	}

	@Override
	public List<Books> getAllBooks() {
		return bookRepository.findAll();
	}

	 @Override
	    public ResponseEntity<String> deleteBookById(Integer bookId) {
	        Optional<Books> bookOpt = bookRepository.findById(bookId);

	        if (bookOpt.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
	        }

	        bookRepository.deleteById(bookId);
	        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
	    }
}
