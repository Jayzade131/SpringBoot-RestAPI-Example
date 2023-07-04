package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.dao.BookRepo;
import com.main.model.Books;

@Component
public class BookService {
	
	@Autowired
	private BookRepo bookrepo;
//	private static List<Books> list = new ArrayList<>();
//
//	static {
//		list.add(new Books(102, "java 8", "abc"));
//		list.add(new Books(103, "servlet", "def"));
//		list.add(new Books(104, "spring", "ghi"));
//
//	}
	// get all books
	public List<Books> getallBooks() {
		List<Books> findAll =(List<Books>) this.bookrepo.findAll();
		return findAll;

	}
	
	//get single book by id
	public Books getBook(int id)
	{
//		Books book=null;
//		try {
//			
//			book = list.stream().filter(e->e.getId()==id).findFirst().get();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
		
		Books findById = this.bookrepo.findById(id);
		
		return findById;
		
	}
	
	//create book
	public Books addBook(Books b) 
	{
//		list.add(b);
		Books b1 = this.bookrepo.save(b);
		return b1;
	}
	
	//delete book
	public void deleteBook(int bid)
	{
//	list=	list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
		this.bookrepo.deleteById(bid);
	}
	
	//update book
	public void updateBook(Books book,int bid)
	{
		
//		list=list.stream().map(b->{
//			if(b.getId()==bid)
//			{
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(bid);
		this.bookrepo.save(book);
//		
	}

}
