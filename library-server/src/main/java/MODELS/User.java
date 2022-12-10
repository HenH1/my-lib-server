package MODELS;

import org.bson.codecs.pojo.annotations.BsonProperty;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static int counterId = 0;

    @BsonProperty(value = "id")
    private int id;
    private String userName;

    @BsonProperty(value = "booksData")
    private List<Book> books; // For showing data in the api.

    private List<Integer> booksId;
    private int favoriteBookId;

    public User() {
    }

    public User(String userName) {
        this.id = this.counterId++;
        this.userName = userName;
        this.books = new ArrayList<>(); // Initialize empty list
        this.booksId = new ArrayList<>(); // Initialize empty list
        this.favoriteBookId = -1; // none
    }

    public User(String userName, int id, int favoriteBookId, List<Book> books, List<Integer> booksId) {
        this.id = id;
        this.userName = userName;
        this.books = books;
        this.booksId = booksId; // Initialize empty list
        this.favoriteBookId = favoriteBookId; // none
    }

    public List<Integer> getBooksId() {
        return booksId;
    }
    public void setBooksId(List<Integer> booksId) {
        this.booksId = booksId;
    }

    public String userName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getFavoriteBookId() {
        return favoriteBookId;
    }

    public void setFavoriteBookId(int favoriteBookId) {
        this.favoriteBookId = favoriteBookId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addBookId(int bookId) {
        this.booksId.add(bookId);
    }

    public void removeBookId(int bookId) {
        this.booksId.remove(Integer.valueOf(bookId));
    }

    public boolean isBookExist(int bookId) {
        return this.booksId.contains(bookId);
    }

}
