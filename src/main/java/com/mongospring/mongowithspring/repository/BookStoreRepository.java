package com.mongospring.mongowithspring.repository;

import com.mongospring.mongowithspring.model.builders.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface BookStoreRepository extends MongoRepository<Book, String> {
	
	@Query("{title:'?0'}")
	Book findItemByTitle(String title);

	@Query("{_id:'?0'}")
	Book findItemById(String id);

	@Query("{rating: ?0}")
	List<Book> findItemByRating(Integer rating);

	@Query("{genres: '?0'}}")
	List<Book> findItemByGenre(String genre);
	
	@Query(value = "{author:'?0'}")
	List<Book> findItemByAuthor(String author);
	
	long count();
}
