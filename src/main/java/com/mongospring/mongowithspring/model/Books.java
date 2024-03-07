package com.mongospring.mongowithspring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.ArrayList;


@Document("books")
public class Books {
		
	@Id
	private String id;
	private String title;
	private String author;
	private Integer pages;
	private Integer rating;
	private List<String> genres = new ArrayList<>();
	private List<Review> reviews = new ArrayList<>();
	
	public Books() {
		
	}

	public Books(String id, String title, String author, Integer pages, Integer rating, List<String> genres, List<Review> reviews) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.rating = rating;
		this.genres = genres;
		this.reviews = reviews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addReviews(Review review) {
		this.reviews.add(review);
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", author=" + author + ", pages=" + pages + ", rating=" + rating
				+ ", genres=" + genres + ", reviews=" + reviews + "]";
	}
	
	
}
