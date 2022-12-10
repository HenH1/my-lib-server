package DAL;

import MODELS.Book;
import CONTROLLER.Consts;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;

public class DalBook extends DBManager{
    private MongoCollection<Book> bookCollection;

    public DalBook() {
        bookCollection = database.getCollection(Consts.COLLECTION_BOOKS, Book.class);
    }

    public ArrayList<Book> getBooks() {
        return bookCollection.find().into(new ArrayList<>());
    }

    public void addBook(Book book) {
        bookCollection.insertOne(book);
    }

    public void updateBook(int bookId, String fieldName, Object updatedVal) {
        bookCollection.updateOne(Filters.eq("id", bookId), Updates.set(fieldName, updatedVal));
    }

    public void deleteBook(int bookId) {
        bookCollection.deleteOne(Filters.eq("id", bookId));
    }

}
