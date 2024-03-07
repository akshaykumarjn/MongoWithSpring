package com.mongospring.mongowithspring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import com.mongospring.mongowithspring.model.Books;

public interface BookStoreRepository extends MongoRepository<Books, String> {
	
	@Query("{title:'?0'}")
	Books findItemByTitle(String title);
	
	
	@Query(value = "{author: '?0'")
	List<Books> findAll(String author);
	
	public long count();
}
