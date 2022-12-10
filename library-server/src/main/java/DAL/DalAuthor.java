package DAL;

import CONTROLLER.Consts;
import MODELS.Book;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class DalAuthor extends DBManager {
    private MongoCollection<Book> bookCollection;

    public DalAuthor() {
        bookCollection = database.getCollection(Consts.COLLECTION_BOOKS, Book.class);
    }

    // Gets the author id for filter and only the name cause it's the only field that can be changed
    public void updateAuthor(int authorId, String updatedName) {
        bookCollection.updateOne(Filters.eq("author._id", authorId), Updates.set("author.authorName", updatedName));
    }

    // Deleting authors means to add null for author field in books collection.
    public void deleteAuthor(int authorId) {
        bookCollection.updateOne(Filters.eq("author._id", authorId), Updates.set("author", null));
    }
}
