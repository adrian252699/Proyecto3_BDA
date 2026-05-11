/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import config.ConexionMongo;
import entidades.Pelicula;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import interfaces.IPeliculaDAO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class PeliculaDAO implements IPeliculaDAO{
    
    private final MongoCollection<Pelicula> coleccion;
    private final MongoCollection<Document> coleccionDoc;

    public PeliculaDAO() {
        this.coleccion = ConexionMongo.getCollection(
                        "peliculas",
                        Pelicula.class
        );
        
        this.coleccionDoc = ConexionMongo.getCollection(
                        "peliculas",
                        Document.class
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
    public Pelicula actualizarPelicula(Pelicula pelicula) throws DaoException, EntityNotFoundException {
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
                throw new EntityNotFoundException("No se encontró la película para actualizar");
            }
            
            return coleccion.find(eq("_id", pelicula.getId())).first();
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible actualizar la pelicula", e);
        }
    }

    @Override
    public boolean eliminarPelicula(ObjectId id) throws DaoException, EntityNotFoundException {
        if (id == null) {
            throw new DaoException("El id es necesario para eliminar");       
        }
        
        try {
            DeleteResult resultado =
                    coleccion.deleteOne(eq("_id", id));

            if (resultado.getDeletedCount() == 0) {
                throw new EntityNotFoundException("Pelicula no encontrada"); 
            }

            return true;
        }catch (MongoException e) {
            throw new DaoException("No fue posible eliminar la pelicula", e);
        }
    }

    @Override
    public Pelicula buscarPeliculaId(ObjectId id) throws DaoException,EntityNotFoundException {
        if (id == null) {
            throw new DaoException("Id Requerido");
        }
        
        try {
            Pelicula peliculaEncontrada =
                    coleccion.find(eq("_id", id)).first();

            if (peliculaEncontrada == null) {
                throw new EntityNotFoundException("Pelicula no encontrada");
            }

            return peliculaEncontrada;
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible buscar la pelicula", e);
        }
    }

    @Override
    public List<Pelicula> listarPeliculas() throws DaoException {
        try {
            
            List<Pelicula> listaPeliculas = coleccion.find().into(new ArrayList<>());
            
            if (listaPeliculas.isEmpty()) {
                return new ArrayList<>();
            }
            
            return listaPeliculas;
        } catch (MongoException e) {
            throw new DaoException("No fue posible listar todas las peliculas", e);
        }
    }

    @Override
    public List<Pelicula> listarPeliculasPaginado(int pagina, int limite) throws DaoException {
        try {
            int skip = (pagina - 1) * limite;
            
            List<Document> pipeline = List.of(new Document("$sort",new Document("createdAt",-1)),
                    new Document("$skip",skip),
                    new Document("$limit",limite)
            );
            
            return coleccion.aggregate(pipeline).into(new ArrayList<>());
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible paginar las peliculas", e);
        }
    }

    @Override
    public List<Pelicula> buscarPorGenero(String genero) throws DaoException {
        try {
            
            if (genero == null) {
                throw new DaoException("Genero de la pelicula Requerido");
            }
            
            List<Pelicula> peliculasGenero = coleccion.find(Filters.eq("generos",genero))
                    .sort(Sorts.ascending("titulo"))
                    .into(new ArrayList<>());

            return peliculasGenero;
            
        } catch (MongoException e) {
            throw new DaoException("No fue buscar por genero", e);
        }
    }

    @Override
    public List<Pelicula> buscarPorClasificacion(String clasificacion) throws DaoException {
        try {
            
            if (clasificacion == null) {
                throw new DaoException("Clasificacion de la pelicula Requerido");
            }
            
            List<Pelicula> peliculasGenero = coleccion.find(Filters.eq("clasificacion",clasificacion))
                    .sort(Sorts.ascending("titulo"))
                    .into(new ArrayList<>());
            
            return peliculasGenero;
            
        } catch (MongoException e) {
            throw new DaoException("No fue buscar por genero", e);
        }
    }
    
}
