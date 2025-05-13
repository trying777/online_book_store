package com.book.store.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer bookId;
	
	@NotBlank(message = "Title is required")
	@Column(name = "book_title", length = 100, nullable = false)
	private String bookTitle;
	
	@NotBlank(message = "Author name should be provided")
	@Column(name = "author_name", length = 60, nullable = false)
	private String authorName;
	
	@NotBlank(message = "Please give description of the book")
	@Column(name = "description", columnDefinition = "Text", length = 200, nullable = false)
	private String description;
	
	@NotNull(message = "Genre is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "book_genre")
	private BookGenre genre;
	
	@NotBlank(message = "Published year must be specified")
	@Column(name = "yop", length = 4, nullable = false)
	@Pattern(regexp = "^[1-2][0-9]{3}$", message = "Year must be between 19th century to 21st century")
	private String yop;
	
	@NotNull(message = "Price is required")
	@Column(name = "book_price", nullable = false)
	private BigDecimal bookPrice;
	
	@Column(name = "quantity", nullable = false)
	@Min(value = 0, message = "Quantity can be 0 or more")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	private User publisher;

	public Books() {
		super();
	}

	public Books(Integer bookId, String bookTitle, String authorName, String description, BookGenre genre,
			 String yop, BigDecimal bookPrice, int quantity, User publisher) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.description = description;
		this.genre = genre;
		this.yop = yop;
		this.bookPrice = bookPrice;
		this.quantity = quantity;
		this.publisher = publisher;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BookGenre getGenre() {
		return genre;
	}

	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	public String getYop() {
		return yop;
	}

	public void setYop(String yop) {
		this.yop = yop;
	}

	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", bookTitle=" + bookTitle + ", authorName=" + authorName + ", description="
				+ description + ", genre=" + genre + ", yop=" + yop + ", bookPrice=" + bookPrice + ", quantity="
				+ quantity + ", publisher=" + publisher + "]";
	}
	
}
