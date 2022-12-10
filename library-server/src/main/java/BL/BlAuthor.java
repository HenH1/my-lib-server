package BL;

import DAL.DalAuthor;
import DAL.DalBook;
import MODELS.Author;
import MODELS.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlAuthor {
    private DalAuthor dalAuthor;
    private DalBook dalBook;

    public  BlAuthor(){
        dalAuthor = new DalAuthor();
        dalBook = new DalBook();
    }

    public List<?> getAuthors() {
        ArrayList<Book> booksList = dalBook.getBooks();
        List<Author> authorsList = booksList.stream().map(Book::getAuthor).filter(author -> author != null).collect(Collectors.toList());
        return authorsList;
    }

    // Gets the author id for filter and only the name cause it's the only field that can be changed
    public void updateAuthor(int authorId, String updatedName) {
        dalAuthor.updateAuthor(authorId,updatedName);
    }

    // Deleting authors means to add null for author field in books collection.
    public void deleteAuthor(int authorId) {
       dalAuthor.deleteAuthor(authorId);
    }

}
