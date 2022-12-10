package CONTROLLER;

import BL.BlBook;
import org.json.JSONArray;
import spark.Request;

import static spark.Spark.get;
import static spark.Spark.post;

public class BookRH {
    public static BlBook blBook = new BlBook();

    public static void apiRoute() {

        get("/books", (request, response) -> BookRH.getBooks());

        post("/deleteBook/:id", (request, response) -> BookRH.deleteBookById(request));

        post("/editBook/:id/name/:name", (request, response) -> BookRH.editBookNameById(request));

    }
        /**
         * @return all the books
         */
    public static Object getBooks() {
        try {
            return new JSONArray(blBook.getBooks());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * @return new list after delete
     */
    public static Object deleteBookById(Request request) {
        try {
            blBook.deleteBook(Integer.parseInt(request.params(":id")));
            return new JSONArray(blBook.getBooks());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static Object editBookNameById(Request request) {
        try {
            blBook.updateBook(Integer.parseInt(request.params(":id")),"bookName",request.params(":name"));
            return new JSONArray(blBook.getBooks());
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
