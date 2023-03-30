package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Book;
import com.example.demo.services.BookService;

@RestController
@RequestMapping(value = "api/v1/books")
@CrossOrigin(origins = "*")
public class BookController {
	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<Book> create(@RequestBody Book book) {
		return new ResponseEntity<Book>(bookService.create(book), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Book book) {
		bookService.update(book);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> getById(@PathVariable(value = "id") Long id) {
		Book book = bookService.getById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
		bookService.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/search/by/title")
	public ResponseEntity<List<Book>> searchByTitles(@RequestParam(value = "bookTitle") String bookTitle) {
		try {
			return new ResponseEntity<>(bookService.searchByTitle(bookTitle), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/search/by/author")
	public ResponseEntity<List<Book>> searchByAuthors(@RequestParam(value = "authorName") String authorName) {
		try {
			return new ResponseEntity<>(bookService.searchByAuthor(authorName), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/search/by/category")
	public ResponseEntity<List<Book>> searchByCategories(@RequestParam(value = "categoryName") String categoryName) {
		try {
			return new ResponseEntity<>(bookService.searchByCategory(categoryName), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<Book>> ByCategoriesAndTitles(
			@RequestParam(value = "categories", required = false) String categories,
			@RequestParam(value = "titles", required = false) String titles) {
		try {
			return new ResponseEntity<>(bookService.search(categories, titles), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAll() {
		try {
			return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}