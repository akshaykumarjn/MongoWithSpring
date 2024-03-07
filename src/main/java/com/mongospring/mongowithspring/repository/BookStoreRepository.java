package com.mongospring.mongowithspring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import com.mongospring.mongowithspring.model.Books;

public interface BookStoreRepository extends MongoRepository<Books, String> {
	
	@Query("{title:'?0'}")
	Books findItemByTitle(String title);

	@Query("{_id:'?0'}")
	Books findItemById(String id);

	@Query("{rating: ?0}")
	List<Books> findItemByRating(Integer rating);

	@Query("{genre: {$elemMatch:'?0'}}")
	List<Books> findItemByGenre(String genre);
	
	@Query(value = "{author:'?0'}")
	List<Books> findItemByAuthor(String author);
	
	public long count();
}
