package MODELS;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Author {
    private static int counterId = 0;
    @BsonProperty(value = "_id")
    private int id;
    private String authorName;

    public Author(){
    }

    public Author(String authorName, int id) {
        this.id = id;
        this.authorName = authorName;
    }

    public Author(String authorName) {
        this.id = counterId++;
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
