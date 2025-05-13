package com.book.store.entity;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Books book;

    private int quantity;

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Long id, User user, Books book, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.book = book;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", user=" + user + ", book=" + book + ", quantity=" + quantity + "]";
	}

   
}
