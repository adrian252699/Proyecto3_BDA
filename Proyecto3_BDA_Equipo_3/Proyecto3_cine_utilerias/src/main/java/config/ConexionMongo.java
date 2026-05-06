/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author jalt2
 */
public class ConexionMongo {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private ConexionMongo() {
        
    }

    static {
        try {

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                    .automatic(true)
                    .build();

            CodecRegistry pojoCodecRegistry = fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(pojoCodecProvider)
            );

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(ConfigMongo.URL))
                    .codecRegistry(pojoCodecRegistry)
                    .build();

            mongoClient = MongoClients.create(settings);

            database = mongoClient.getDatabase(ConfigMongo.DB_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static <T> MongoCollection<T> getCollection(String nombre, Class<T> clase) {
        return database.getCollection(nombre, clase);
    }
}
