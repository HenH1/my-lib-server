package CONTROLLER;

import BL.BlUser;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.post;

public class UserRH {

    public static BlUser blUser = new BlUser();

    public static void apiRoute(){
        get("/users", (request, response) -> getUsers());

        get("/users/books/:id", (request, response) -> getUsersByBookId((request)));

        post("/deleteUser/:id", (request, response) -> deleteUserById(request));

        get("/user/:id", (request, response) -> getUser(request));

        post("/users/:userId/deleteBook/:bookId", (request, response) -> deleteBookFromUser(request));

        post("/users/:userId/addBook/:bookId", (request, response) -> addBookToUser(request));

        post("/users/:userId/editFavorite/:bookId", (request, response) -> editFavoriteBook(request));

        post("/editUser/:id/name/:name", (request, response) -> editUserNameById(request));
    }

    /**
     * @return all the users
     */
    public static Object getUsers() {
        try {
            return new JSONArray(blUser.getUsers());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * @return user by id
     */
    public static Object getUser(Request request) {
        try {
            return new JSONObject(blUser.getUserById(parseInt(request.params(":id"))));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * @return all the users that reads specific book (filtered by book id)
     */
    public static Object getUsersByBookId(Request request) {
        try {
            return new JSONArray(blUser.getUsersByBookId(parseInt(request.params(":id"))));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * @return new list after delete
     */
    public static Object deleteUserById(Request request) {
        try {
            blUser.deleteUser(parseInt(request.params(":id")));
            return new JSONArray(blUser.getUsers());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static Object deleteBookFromUser(Request request) {
        try {
            int userId = Integer.parseInt(request.params(":userId"));
            int bookId = Integer.parseInt(request.params(":bookId"));

            blUser.deleteBook(userId, bookId);
            return new JSONObject(blUser.getUserById(userId));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static Object addBookToUser(Request request) {
        int userId = Integer.parseInt(request.params(":userId"));
        int bookId = Integer.parseInt(request.params(":bookId"));

        blUser.addBook(userId, bookId);
        return new JSONObject(blUser.getUserById(userId));
    }

    public static Object editFavoriteBook(Request request) {
        int userId = Integer.parseInt(request.params(":userId"));
        int bookId = Integer.parseInt(request.params(":bookId"));

        return  new JSONObject(blUser.editFavoriteBook(userId,bookId));
    }

    public static Object editUserNameById(Request request) {
        try {
            blUser.updateUser(Integer.parseInt(request.params(":id")),"userName",request.params(":name"));
            return new JSONArray(blUser.getUsers());
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
