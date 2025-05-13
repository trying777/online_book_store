package com.book.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.entity.Books;
import com.book.store.entity.User;
import com.book.store.entity.UserRole;
import com.book.store.repository.BookRepository;
import com.book.store.repository.UserRepository;
import com.book.store.service.BookService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookController {


	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;
		
	@Autowired
	private UserRepository userRepository;
	
//	http://localhost:8080/addBooks/{uname}
	@PostMapping("/addBooks/{uname}")
	public ResponseEntity<Books> addBooks(@PathVariable("uname") String username, @Valid @RequestBody Books books){
		
		User userExist = userRepository.findByUserName(username);
		
		if(userExist == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		if(!(userExist.getRole()== UserRole.PUBLISHER)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		books.setPublisher(userExist);
		Books savedBook = bookService.addBooks(books);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}
	
//	http://localhost:8080/findBookById/{bookid}
	@GetMapping("/findBookById/{bookid}")
	public ResponseEntity<Books> findBookById(@PathVariable("bookid") Integer bookId){
		Books books = bookService.findBookById(bookId);
		if(books == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(books);
	}
	
	@PostMapping("/updateBook/{uname}/{bid}")
	public ResponseEntity<?> updateBookByBookId(@PathVariable("bid") Integer bookId,
	                                            @PathVariable("uname") String username,
	                                             @Valid @RequestBody Books books) {

	    User userExist = userRepository.findByUserName(username);

	    if (userExist == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }

	    if (userExist.getRole() != UserRole.PUBLISHER) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only publishers can update books");
	    }

	    Books existingBook = bookService.findBookById(bookId);
	    if (existingBook == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
	    }

	    if (!existingBook.getPublisher().getUserName().equals(username)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Only the publisher of this book can update it.");
	    }

	    // Copy updated fields from input to existing book
	    existingBook.setBookTitle(books.getBookTitle());
	    existingBook.setAuthorName(books.getAuthorName());
	    existingBook.setDescription(books.getDescription());
	    existingBook.setGenre(books.getGenre());
	    existingBook.setYop(books.getYop());
	    existingBook.setBookPrice(books.getBookPrice());
	    existingBook.setQuantity(books.getQuantity());

	    Books updatedBook = bookService.updateBookByBookId(existingBook);

	    return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
	}
	
//	http://localhost:8080/getAllBooks
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Books>> getAllBooks(){
		List<Books> books = bookService.getAllBooks();
	    return ResponseEntity.ok(books);
	}
	
//	http://localhost:8080/deleteBookById/{uname}/{bookid}
	@DeleteMapping("/deleteBookById/{uname}/{bookid}")
	public ResponseEntity<String> deleteBookById(@PathVariable("uname") String username,
	                                             @PathVariable("bookid") Integer bookId) {

	    Optional<Books> optionalBook = bookRepository.findById(bookId);
	    if (optionalBook.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
	    }

	    Books book = optionalBook.get();
	    User user = userRepository.findByUserName(username);

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }

	    if (user.getRole() != UserRole.PUBLISHER) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only publishers can delete books");
	    }

	    if (book.getPublisher() == null || !book.getPublisher().getUserName().equals(username)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not the publisher of this book");
	    }

	    return bookService.deleteBookById(bookId);
	}
}
