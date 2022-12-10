package DAL;

import MODELS.User;
import CONTROLLER.Consts;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import static com.mongodb.client.model.Aggregates.*;
import static java.util.Collections.singletonList;

public class DalUser extends DBManager {

    private MongoCollection<User> userCollection;

    public DalUser() {
        userCollection = database.getCollection(Consts.COLLECTION_USERS, User.class);
    }

    // Getting users list (including books data)
    public ArrayList<User> getUsers() {
        Bson pipeline = lookup(Consts.COLLECTION_BOOKS, "booksId", "id", "booksData");
        return userCollection.aggregate(singletonList(pipeline)).into(new ArrayList<>());
    }

    public ArrayList<User> getUsersByBookId(int bookId) {
        return userCollection.find(Filters.eq("booksId", bookId)).into(new ArrayList<>());
    }

    public void addUser(User user) {
        userCollection.insertOne(user);
    }

    public void replaceUser(int userId, User user) {
        userCollection.replaceOne(Filters.eq("id", userId), user);
    }

    public void updateUser(int userId, String updateFieldName, Object updatedVal) {
        userCollection.updateOne(Filters.eq("id", userId), Updates.set(updateFieldName, updatedVal));
    }

    public void deleteUser(int userId) {
        userCollection.deleteOne(Filters.eq("id", userId));
    }

}
