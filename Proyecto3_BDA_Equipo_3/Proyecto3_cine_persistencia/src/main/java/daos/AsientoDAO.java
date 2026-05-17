/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import config.ConexionMongo;
import embebidos.Asiento;

/**
 *
 * @author jalt2
 */
public class AsientoDAO {
    private final MongoCollection<Asiento> coleccion;

    public AsientoDAO() {
        this.coleccion = ConexionMongo.getCollection(
            "asientos",
            Asiento.class
        );
        
        
    }
}
