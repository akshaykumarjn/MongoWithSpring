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

    Scanner sc = new Scanner(System.in);
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

        while(true) {
            System.out.println("1. View all books in the catalog");
            System.out.println("2. Add books to the catalog");
            System.out.println("3. Search books by ID");
            System.out.println("4. Search books by author");
            System.out.println("5. Search books by title");
            System.out.println("6. Search books by rating");
            System.out.println("7. Search books by genre");
            System.out.println("8. Exit");
            System.out.print("Enter an option from the above list: ");
            int optionSelected = sc.nextInt();
            sc.nextLine();

            if(optionSelected < 1 || optionSelected > 8){
                System.out.println("Incorrect option selected, please try again");
                continue;
            }

            switch (optionSelected) {
                case 1 -> {
                    books = bookStoreRepo.findAll();
                    displayData(books);
                }
                case 2 -> addNewBooksToCatalog();
                case 3 -> {
                    System.out.println("Enter a ID: ");
                    String id = sc.nextLine();
                    displayData(List.of(bookStoreRepo.findItemById(id)));
                }
                case 4 -> {
                    System.out.println("Enter an author: ");
                    String author = sc.nextLine();
                    displayData(bookStoreRepo.findItemByAuthor(author));
                }
                case 5 -> {
                    System.out.println("Enter a title: ");
                    String title = sc.nextLine();
                    displayData(List.of(bookStoreRepo.findItemByTitle(title)));
                }
                case 6 -> {
                    System.out.println("Enter a rating: ");
                    int rating = sc.nextInt(); sc.nextLine();
                    displayData(bookStoreRepo.findItemByRating(rating));
                }
                case 7 -> {
                    System.out.println("Enter a genre: ");
                    String genre = sc.nextLine();
                    displayData(bookStoreRepo.findItemByGenre(genre));
                }
                case 8 -> {
                    sc.close();
                    System.exit(0);
                }
            }
        }
    }

    private void addNewBooksToCatalog() {
        System.out.println("\n************************");
        System.out.println("Enter details to add new books to the library!");

        char addAnother = 'Y';
        int idSuffix = 7;


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
        if(books.isEmpty())
            System.out.println("No data found for this query.");
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


