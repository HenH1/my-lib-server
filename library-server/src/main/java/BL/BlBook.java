package BL;

import DAL.DalBook;
import MODELS.Book;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;

public class BlBook {
    private DalBook dalBook;

    public  BlBook(){
        dalBook = new DalBook();
    }
    public ArrayList<Book> getBooks() {
        return dalBook.getBooks();
    }

    public void addBook(Book book) {
        dalBook.addBook(book);
    }

    public void updateBook(int bookId, String fieldName, Object updatedVal) {
        dalBook.updateBook(bookId, fieldName, updatedVal);
    }

    public void deleteBook(int bookId) {
        dalBook.deleteBook(bookId);
    }
}
