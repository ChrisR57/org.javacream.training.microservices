package org.javacream.training.books.service.web;

import java.util.Collection;

import org.javacream.training.books.service.api.Book;
import org.javacream.training.books.service.api.BookException;
import org.javacream.training.books.service.api.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BooksWebService {

	@Autowired
	private BooksService booksService;

	@PostMapping(path = "books")
	public String newBook(@RequestParam("title") String title) throws BookException {
		try {
			return booksService.newBook(title);
		} catch (Exception be) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping(path = "books/{isbn}", produces = "application/json")
	public Book findBookByIsbn(@PathVariable("isbn") String isbn) throws BookException {
		try {
			return booksService.findBookByIsbn(isbn);
		} catch (Exception be) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "books", consumes = "application/json")
	public Book updateBook(@RequestBody Book book) throws BookException {
		try {
			return booksService.updateBook(book);
		} catch (Exception be) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@DeleteMapping(path = "books/{isbn}")
	public void deleteBookByIsbn(@PathVariable("isbn") String isbn) throws BookException {
		try {
			booksService.deleteBookByIsbn(isbn);
		} catch (Exception be) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	public Collection<Book> findAllBooks() {
		return booksService.findAllBooks();
	}

}
