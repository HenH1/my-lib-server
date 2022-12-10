package CONTROLLER;

import MODELS.Author;
import MODELS.Book;
import MODELS.User;
import DAL.DBManager;
import DAL.DalBook;
import DAL.DalUser;
import spark.Spark;

import static spark.Spark.*;

public class Main {
    public static DBManager dbManager = new DBManager();
    public static DalUser dalUser = new DalUser();
    public static DalBook dalBook = new DalBook();

    public static void main(String[] args) {

        createCollections();
        addData();
        apiRouting();

        // On Shutdown of the server I want to delete the collections so in the next run all thw flow will work.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutdown Hook is running !");
                dbManager.dropCollection(Consts.COLLECTION_USERS);
                dbManager.dropCollection(Consts.COLLECTION_BOOKS);
            }
        });

        Spark.before((request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Credentials", "true");
            response.header("Access-Control-Allow-Origin", "*");
        });

    }

    public static void createCollections() {
        dbManager.createCollection(Consts.COLLECTION_USERS);
        dbManager.createCollection(Consts.COLLECTION_BOOKS);
    }

    public static void addData() {
        User[] usersArray = {new User("עידן דוידי"),
                new User("אודי דורון"),
                new User("אלכס קוביצה")};

        Author[] authorArray = {new Author("פרופ׳ דן אריאלי"),
                new Author("פרופ׳ יובל נח הררי"),
                new Author("ג.ק. רולינג")};

        int counterIndex = 0;

        Book[] booksArray = {new Book("שם לא רציונלי ולא במקרה", authorArray[counterIndex++]),
                new Book("קיצור תולדות האנושות", authorArray[counterIndex++]),
                new Book("הארי פוטר", authorArray[counterIndex++])};
        usersArray[0].addBookId(0);
        usersArray[0].addBookId(1);
        usersArray[1].addBookId(1);
        usersArray[1].addBookId(2);

        for (User user : usersArray) {
            dalUser.addUser(user);
        }

        for (Book book : booksArray) {
            dalBook.addBook(book);
        }
    }

    public static void apiRouting() {
        get("/alive", (req, res) -> "Ok");

        UserRH.apiRoute();
        BookRH.apiRoute();
        AuthorRH.apiRoute();
    }
}

