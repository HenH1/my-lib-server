package MODELS;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Book {
    private static int counterId = 0;
    @BsonProperty(value = "id")
    private int id;
    private String bookName;
    private Author author;

    public Book() {
    }

    public Book(String bookName, Author author) {
        this.id = counterId++;
        this.bookName = bookName;
        this.author = author;
    }

    public Book(String bookName, Author author, int id) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
