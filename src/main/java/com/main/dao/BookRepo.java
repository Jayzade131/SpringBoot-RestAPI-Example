package com.main.dao;

import org.springframework.data.repository.CrudRepository;

import com.main.model.Books;

public interface BookRepo extends CrudRepository<Books, Integer> {
	public Books findById(int id);
	
}
