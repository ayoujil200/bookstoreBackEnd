package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book create(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> search(String categories, String titles) {
		String[] categoriesArray = categories != null ? categories.split(",") : null;
		Set<String> categoriesSet = categoriesArray != null ? new HashSet<>(Arrays.asList(categoriesArray)) : null;
		String[] titlesArray = titles != null ? titles.split(",") : null;
		List<Book> books = new ArrayList<>();

		if (categories == null && titles == null) {
			return bookRepository.findAll();
		}

		if (categories != null && titles != null) {
			for (String title : titlesArray) {
				for (String category : categoriesSet) {
					books.addAll(bookRepository.findByCategoriesContainingAndTitleContaining(category, title));
				}
			}
		}

		if (categories != null && titles == null) {
			for (String category : categoriesSet) {
				books.addAll(searchByCategories(category));
			}
		}

		if (categories == null && titles != null) {
			for (String title : titlesArray) {
				books.addAll(searchByTitles(title));
			}
		}

		books = books.stream().distinct().collect(Collectors.toList());

		return books;
	}

	@Override
	public List<Book> searchByTitles(String title) {
		List<Book> resultBooks = new ArrayList<>();
		resultBooks.addAll(bookRepository.findByTitleContaining(title));
		return resultBooks;
	}

	@Override
	public List<Book> searchByCategories(String category) {
		List<Book> resultBooks = new ArrayList<>();
		resultBooks.addAll(bookRepository.findByCategoriesContaining(category.trim()));
		return resultBooks;
	}
	
	public List<Book> _searchByAuthors(String author) {
		List<Book> resultBooks = new ArrayList<>();
		resultBooks.addAll(bookRepository.findByAuthorContaining(author));
		return resultBooks;
	}

	@Override
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book getById(Long id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public void update(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Book> searchByTitle(String title) {
		List<Book> books = new ArrayList<>();
		String[] titlesArray = title != null ? title.split(",") : null;
		for (String _title : titlesArray) {
			books.addAll(searchByTitles(_title));
		}
		books = books.stream().distinct().collect(Collectors.toList());
		return books;
	}

	@Override
	public List<Book> searchByCategory(String category) {
		String[] categoriesArray = category != null ? category.split(",") : null;
		Set<String> categoriesSet = categoriesArray != null ? new HashSet<>(Arrays.asList(categoriesArray)) : null;
		List<Book> books = new ArrayList<>();

		for (String categoryObbject : categoriesSet) {
			books.addAll(searchByCategories(categoryObbject));
		}

		books = books.stream().distinct().collect(Collectors.toList());

		return books;
	}

	@Override
	public List<Book> searchByAuthor(String authors) {
		List<Book> books = new ArrayList<>();
		String[] authorsArray = authors != null ? authors.split(",") : null;
		for (String author : authorsArray) {
			books.addAll(_searchByAuthors(author));
		}
		books = books.stream().distinct().collect(Collectors.toList());
		return books;
	}

}
