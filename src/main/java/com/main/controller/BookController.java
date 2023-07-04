package com.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Books;
import com.main.service.BookService;

@RestController
public class BookController {
	@Autowired
	BookService bookService;
	@GetMapping("/books")
	public ResponseEntity<List<Books>> getBook()
	{
		List<Books> getallBooks = this.bookService.getallBooks();
		if(getallBooks.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(getallBooks));
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Books> getbookbyId(@PathVariable("id") int id) {
		Books bo = this.bookService.getBook(id);
		if(bo==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(bo));
		
	}
	
	@PostMapping("/boo")
	public ResponseEntity<Books> addBooks(@RequestBody Books book)
	{
		 Books addBook=null;
		 try {
			 addBook = this.bookService.addBook(book);
			 return ResponseEntity.of(Optional.of(addBook));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		 
		
	}
	@DeleteMapping("bookd/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id")int id)
	{
		try {
			this.bookService.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
			
	@PutMapping("booku/{id}")
	public ResponseEntity<Books> updateBook(@RequestBody Books book,@PathVariable("id")int id)
	{
		try {
			 this.bookService.updateBook(book, id);
			 return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}

}
