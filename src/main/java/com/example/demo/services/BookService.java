package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Book;

public interface BookService {
	public Book create(Book book);

	public void update(Book book);

	public void delete(Long id);

	public Book getById(Long id);

	public List<Book> searchByTitles(String titles);

	public List<Book> searchByCategories(String categories);

	public List<Book> search(String categories, String titles);

	public List<Book> searchByTitle(String title);

	public List<Book> searchByCategory(String category);

	public List<Book> searchByAuthor(String author);

	public List<Book> getAll();
}
