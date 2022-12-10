package BL;

import DAL.DalUser;
import MODELS.User;
import org.json.JSONObject;
import spark.Request;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BlUser {
    private DalUser dalUser;

    public  BlUser(){
        dalUser = new DalUser();
    }

    // Getting users list (including books data)
    public ArrayList<User> getUsers() {
        return dalUser.getUsers();
    }

    public ArrayList<User> getUsersByBookId(int bookId) {
        return dalUser.getUsersByBookId(bookId);
    }

    public void addUser(User user) {
        dalUser.addUser(user);
    }

    public void replaceUser(int userId, User user) {
        dalUser.replaceUser(userId, user);
    }

    public void updateUser(int userId, String updateFieldName, Object updatedVal) {
        dalUser.updateUser(userId, updateFieldName, updatedVal);
    }

    public void deleteUser(int userId) {
        dalUser.deleteUser(userId);
    }

    public User getUserById(int id) {
        User selectdUser = dalUser.getUsers().stream().filter(user -> user.getId() == id).collect(Collectors.toList()).get(0);
        return selectdUser;
    }

    // Delete book from user array
    public void deleteBook(int userId, int bookId) {
        User user = getUserById(userId);
        if(user.getFavoriteBookId() == bookId) {
            user.setFavoriteBookId(-1);
        }
        user.removeBookId(bookId);
        dalUser.replaceUser(userId, user);
    }

    // Add book from user array
    public void addBook(int userId, int bookId) {
        User user = getUserById(userId);
        user.addBookId(bookId);
        dalUser.replaceUser(userId, user);
    }

    public Object editFavoriteBook(int userId, int bookId) {
        User user = getUserById(userId);
        int updatedBookId = -1;
        if(user.isBookExist(bookId)){
            if (user.getFavoriteBookId() != bookId) {
                updatedBookId = bookId;
            }
            updateUser(userId, "favoriteBookId", updatedBookId);
            return getUserById(userId);
        }
        return "Book does not exist in your list of books";
    }

}
