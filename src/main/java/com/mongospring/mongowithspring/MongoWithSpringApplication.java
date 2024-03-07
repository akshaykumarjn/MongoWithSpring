package com.mongospring.mongowithspring;

import com.mongospring.mongowithspring.model.Books;
import com.mongospring.mongowithspring.model.Review;
import com.mongospring.mongowithspring.repository.BookStoreRepository;
import de.vandermeer.asciitable.AsciiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableMongoRepositories
public class MongoWithSpringApplication implements CommandLineRunner {

    @Autowired
    BookStoreRepository bookStoreRepo;

    public static void main(String[] args) {
        SpringApplication.run(MongoWithSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {

        System.out.println("Welcome to BookStore!".toUpperCase());

        System.out.println("Below are the titles currently present in the BookStore\n");
        List<Books> books = bookStoreRepo.findAll();
        displayData(books);

        addNewBooksToCatalog();
    }

    private void addNewBooksToCatalog() {
        System.out.println("\n************************");
        System.out.println("Enter details to add new books to the library!");

        char addAnother = 'Y';
        int idSuffix = 7;
        Scanner sc = new Scanner(System.in);

        while (addAnother == 'Y') {
            System.out.println("Enter the title of the book");
            String title = sc.nextLine();

            System.out.println("Enter the author of the book");
            String author = sc.nextLine();

            System.out.println("Enter the number of pages in the book");
            int pages = sc.nextInt();

            System.out.println(
                    "Enter the ratings for the book. **Ratings Range from 0 - 10, 0 being the worst and 10 being the best");
            int ratings = sc.nextInt();
            sc.nextLine();
            System.out.println(
                    "Enter the Genre this book could be categorized under: (You can enter multiple genres separated by a comma");
            String genres = sc.nextLine();

            System.out.println("Enter a review of the book.");
            System.out.println("Enter review title");
            String commentTitle = sc.nextLine();

            System.out.println("Enter the description of the review");
            String commentDescription = sc.nextLine();

            String id = "BK00" + idSuffix;
            idSuffix += 1;

            Books book = new Books(id, title, author, pages, ratings, Arrays.asList(genres.split(",")),
                    List.of(new Review(commentTitle, commentDescription)));
            bookStoreRepo.save(book);

            System.out.println("\nBook Saved Successfully!!");
            System.out.println("Do you want to save another book? Type Y for Yes, N for No");
            addAnother = sc.nextLine().toUpperCase().charAt(0);
            System.out.println("**********************************");
        }
    }

    private void displayData(List<Books> books) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Title", "Author", "Pages", "Rating", "Genres");

        for (Books b : books) {
            table.addRule();
            table.addRow(b.getTitle(), b.getAuthor(), b.getPages(), b.getRating(), b.getGenres());

        }
        table.addRule();
        System.out.println(table.render());
    }
}


