package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	public List<Book> findByCategoriesContaining(String category);

	public List<Book> findByCategoriesContainingAndTitleContaining(String title, String category);

	public List<Book> findByTitleContaining(String title);

	public List<Book> findByAuthorContaining(String author);
}
