package CONTROLLER;

import BL.BlAuthor;
import org.json.JSONArray;
import spark.Request;

import static spark.Spark.get;
import static spark.Spark.post;

public class AuthorRH {
    public static BlAuthor blAuthor = new BlAuthor();

    public static void apiRoute(){

        get("/authors", (request, response) -> AuthorRH.getAuthors());

        post("/deleteAuthor/:id", (request, response) -> AuthorRH.deleteAuthorById(request));

        post("/editAuthor/:id/name/:name", (request, response) -> AuthorRH.editAuthorNameById(request));

    }
    /**
     * @return all the authors
     */
    public static Object getAuthors() {
        try {
            return new JSONArray(blAuthor.getAuthors());
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    /**
     * @return new list after delete
     */
    public static Object deleteAuthorById(Request request) {
        try {
            blAuthor.deleteAuthor(Integer.parseInt(request.params(":id")));
            return new JSONArray(blAuthor.getAuthors());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static Object editAuthorNameById(Request request) {
        try {
            blAuthor.updateAuthor(Integer.parseInt(request.params(":id")),request.params(":name"));
            return new JSONArray(blAuthor.getAuthors());
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
