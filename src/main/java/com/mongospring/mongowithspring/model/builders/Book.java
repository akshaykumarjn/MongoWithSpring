package com.mongospring.mongowithspring.model.builders;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document("books")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private Integer pages;
    private Integer rating;
    private List<String> genres = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    private Book() {
    }

    private Book(BooksBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;;
        this.author = builder.author;
        this.pages = builder.pages;
        this.rating = builder.rating;
        this.genres = builder.genres;
        this.reviews = builder.reviews;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getRating() {
        return rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public static BooksBuilder builder() {
        return new BooksBuilder();
    }
    public static class BooksBuilder {
        private String id;
        private String title;
        private String author;
        private Integer pages;
        private Integer rating;
        private List<String> genres = new ArrayList<>();
        private List<Review> reviews = new ArrayList<>();

        private BooksBuilder(){/* Private Constructor */}

        public BooksBuilder id(String id) {
            this.id = id;
            return this;
        }
        public BooksBuilder title(String title) {
            this.title = title;
            return this;
        }
        public BooksBuilder author(String author) {
            this.author = author;
            return this;
        }
        public BooksBuilder pages(int pages) {
            this.pages = pages;
            return this;
        }
        public BooksBuilder rating(int rating) {
            this.rating = rating;
            return this;
        }
        public BooksBuilder genres(List<String> genres) {
            this.genres = genres;
            return this;
        }
        public BooksBuilder reviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
