package DAL;

import CONTROLLER.Consts;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class DBManager {
    static protected MongoDatabase database;

    public DBManager() {
        if(database == null) {
            this.connect();
            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            database = database.withCodecRegistry(pojoCodecRegistry);
        }
    }

    /* Connect to the db */
    private void connect() {
        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);
        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", Consts.DB_NAME,
                "password".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        database = mongo.getDatabase(Consts.DB_NAME);
        System.out.println("Credentials ::" + credential);
    }

    /* Create collection and return true if succeeded */
    public void createCollection(String collectionName) {
        //Creating a collection
        database.createCollection(collectionName);
        System.out.println("Collection " + collectionName + " created successfully");
    }

    /* Drop collection */
    public void dropCollection(String collectionName) {

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection(collectionName);
        // Dropping a Collection
        collection.drop();
        System.out.println("Collection dropped successfully");
    }
}
