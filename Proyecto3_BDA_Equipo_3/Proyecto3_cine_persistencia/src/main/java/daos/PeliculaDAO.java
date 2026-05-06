/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import config.ConexionMongo;
import entidades.Pelicula;
import excepciones.DaoException;
import interfaces.IPeliculaDAO;
import java.time.Instant;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class PeliculaDAO implements IPeliculaDAO{
    
    private final MongoCollection<Pelicula> coleccion;

    public PeliculaDAO(MongoCollection<Pelicula> coleccion) {
        this.coleccion = ConexionMongo.getCollection(
                        "pelicula",
                        Pelicula.class
                );
    }
    
    

    @Override
    public Pelicula guardarPelicula(Pelicula pelicula) throws DaoException {
        try {
            
            if (pelicula == null) {
                throw new DaoException("La película no puede ser null");
            }
            
            Instant now = Instant.now();
            
            pelicula.setCreatedAt(now);
            pelicula.setUpdatedAt(now);
            this.coleccion.insertOne(pelicula);
            
            return pelicula;
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible guardar la pelicula", e);
        }
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws DaoException {
        try {
            if (pelicula == null) {
                throw new DaoException("La película no puede ser null");
            }
            
            if (pelicula.getId()==null) {
                throw new DaoException("El id es necesario para actualizar");
            }
            
            Instant now = Instant.now();
            
            UpdateResult resultado = coleccion.updateOne(
                            eq("_id", pelicula.getId()),
                            combine(
                                    set("titulo", pelicula.getTitulo()),
                                    set("generos", pelicula.getGeneros()),
                                    set("duracion", pelicula.getDuracion()),
                                    set("clasificacion", pelicula.getClasificacion()),
                                    set("updatedAt", now)
                            )
                    );
            
            if (resultado.getMatchedCount() == 0) {
                throw new DaoException("No se encontró la película para actualizar");
            }
            
            return coleccion.find(eq("_id", pelicula.getId())).first();
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible actualizar la pelicula", e);
        }
    }

    @Override
    public boolean eliminarPelicula(ObjectId _id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Pelicula buscarPeliculaId(ObjectId _id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pelicula> listarPeliculas() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
