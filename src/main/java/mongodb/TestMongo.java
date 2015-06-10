package mongodb;
import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class TestMongo {

    public static void main(String[] args) throws ParseException {
//        MongoClient mongoClient = new MongoClient("10.245.15.151");
      MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://sa:sa@10.245.15.151/test"));
//      MongoClient mongoClient = new MongoClient(new ServerAddress("10.245.15.151"), Arrays.asList(new MongoCredential[]{MongoCredential.createCredential("sa", "test", "sa".toCharArray())}));
        MongoDatabase db = mongoClient.getDatabase("test");
        
        System.out.println(db.getName());
        
//        db.createCollection("restaurants");

        System.out.println(db.getCollection("restaurants").count());
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        db.getCollection("restaurants").insertOne(
                new Document("address",
                        new Document()
                                .append("street", "2 Avenue")
                                .append("zipcode", "10075")
                                .append("building", "1480")
                                .append("coord", asList(-73.9557413, 40.7720266)))
                        .append("borough", "Manhattan")
                        .append("cuisine", "Italian")
                        .append("grades", asList(
                                new Document()
                                        .append("date", format.parse("2014-10-01T00:00:00Z"))
                                        .append("grade", "A")
                                        .append("score", 11),
                                new Document()
                                        .append("date", format.parse("2014-01-16T00:00:00Z"))
                                        .append("grade", "B")
                                        .append("score", 17)))
                        .append("name", "Vella")
                        .append("restaurant_id", "41704620"));

        System.out.println(db.getCollection("restaurants").count());
        
        
        FindIterable<Document> iterable = db.getCollection("restaurants").find(
                new Document("borough", "Manhattan").append("cuisine", "Italian"));
        
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
        
    }
    
    

}
