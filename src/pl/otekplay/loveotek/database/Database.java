package pl.otekplay.loveotek.database;

import com.google.gson.Gson;
import com.mongodb.async.client.FindIterable;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import org.bson.Document;

import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private static MongoDatabase database;
    private static ConcurrentHashMap<Class<?>, MongoCollection<Document>> cache = new ConcurrentHashMap<>();
    private static Gson gson = new Gson();

    public static void init() {
        database = MongoClients.create().getDatabase("otek_database");
    }

    private static MongoCollection<Document> collection(Object object) {
        return collection(object.getClass());
    }

    private static MongoCollection<Document> collection(Class<?> clazz) {
        if (!cache.containsKey(clazz)) {
            cache.put(clazz, database.getCollection(clazz.getSimpleName().toLowerCase() + "s"));
        }

        return cache.get(clazz);
    }

    public static void insert(Object object) {
        collection(object).insertOne(Document.parse(gson.toJson(object)), (aVoid, throwable) -> {});
    }

    public static void synchronize(Object object, Document document) {
        collection(object).findOneAndReplace(document, Document.parse(gson.toJson(object)), (deleteResult, throwable) -> {});
    }

    public static void remove(Object object, Document document) {
        collection(object).deleteOne(document, (deleteResult, throwable) -> {});
    }

    public static FindIterable<Document> find(String collection, Document document) {
        return database.getCollection(collection).find(document);
    }

    public static FindIterable<Document> find(Class<?> clazz, Document document) {
        return collection(clazz).find(document);
    }

    public static FindIterable<Document> find(String collection) {
        return database.getCollection(collection).find();
    }

    public static FindIterable<Document> find(Class<?> clazz) {
        return collection(clazz).find();
    }

    public static Gson gson() {
        return gson;
    }
}
