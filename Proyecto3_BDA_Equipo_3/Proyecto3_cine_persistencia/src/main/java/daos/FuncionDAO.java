/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Indexes;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import config.ConexionMongo;
import entidades.Funcion;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import interfaces.IFuncionDAO;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class FuncionDAO implements IFuncionDAO{
    private final MongoCollection<Funcion> coleccion;
    private final MongoCollection<Document> coleccionDoc;

    public FuncionDAO() {
        this.coleccion = ConexionMongo.getCollection(
                        "funciones",
                        Funcion.class
        );
        this.coleccionDoc = ConexionMongo.getCollection(
                        "funciones",
                        Document.class
        );
        
        this.coleccion.createIndex(
            Indexes.ascending("peliculaId")
        );

        this.coleccion.createIndex(
            Indexes.ascending("fecha")
        );

        this.coleccion.createIndex(
            Indexes.compoundIndex(
                Indexes.ascending("sala.numSala"),
                Indexes.ascending("fecha"),
                Indexes.ascending("hora")
            )
        );
    }

    @Override
    public Funcion guardarFuncion(Funcion funcion) throws DaoException {
        try {
            
            if (funcion == null) {
                throw new IllegalArgumentException("La funcion no puede ser null");
            }
            
            Instant now = Instant.now();
            
            funcion.setCreatedAt(now);
            funcion.setUpdatedAt(now);
            this.coleccion.insertOne(funcion);
            
            return funcion;
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible guardar la funcion", e);
        }
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws DaoException, EntityNotFoundException {
        try {
            if (funcion == null) {
                throw new IllegalArgumentException("La funcion no puede ser null");
            }
            
            if (funcion.getId()==null) {
                throw new IllegalArgumentException("El id es necesario para actualizar");
            }
            
            Instant now = Instant.now();
            
            UpdateResult resultado = coleccion.updateOne(
                            eq("_id", funcion.getId()),
                            combine(
                                    set("peliculaId", funcion.getPeliculaId()),
                                    set("sala", funcion.getSala()),
                                    set("fecha", funcion.getFecha()),
                                    set("hora", funcion.getHora()),
                                    set("updatedAt", now)
                            )
                    );
            
            if (resultado.getMatchedCount() == 0) {
                throw new EntityNotFoundException("No se encontró la funcion para actualizar");
            }
            
            return coleccion.find(eq("_id", funcion.getId())).first();
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible actualizar la funcion", e);
        }
    }

    @Override
    public boolean desactivarFuncion(ObjectId id) throws DaoException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Funcion invalida");
            }
            
            Instant now = Instant.now();
            
            UpdateResult resultado = coleccion.updateOne(
                eq("_id", id),
                combine(
                        set("activo",false),
                        set("updatedAt",now)
                )
            );
            
            if (resultado.getMatchedCount() == 0) {
                return false;
            }
            
            return resultado.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DaoException("Error al desactivar funcion",e);
        }
    }

    @Override
    public Funcion buscarPorId(ObjectId id) throws DaoException, EntityNotFoundException {
       if (id == null) {
            throw new IllegalArgumentException("Id Requerido");
        }
        
        try {
            Funcion funcionEncontrada =
                    coleccion.find(eq("_id", id)).first();

            if (funcionEncontrada == null) {
                throw new EntityNotFoundException("Funcion no encontrada");
            }

            return funcionEncontrada;
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible buscar la funcion", e);
        }
    }

    @Override
    public List<Funcion> listarFunciones() throws DaoException {
       try {
            
            List<Funcion> listaFunciones = coleccion.find().into(new ArrayList<>());
            
            if (listaFunciones.isEmpty()) {
                return new ArrayList<>();
            }
            
            return listaFunciones;
        } catch (MongoException e) {
            throw new DaoException("No fue posible listar las funciones", e);
        } 
    }

    @Override
    public List<Funcion> listarFuncionesPaginado(int pagina, int limite) throws DaoException {
        try {
            int skip = (pagina - 1) * limite;
            
            List<Document> pipeline = List.of(new Document("$sort",new Document("createdAt",-1)),
                    new Document("$skip",skip),
                    new Document("$limit",limite)
            );
            
            return coleccion.aggregate(pipeline).into(new ArrayList<>());
            
        } catch (MongoException e) {
            throw new DaoException("No fue posible paginar las funciones", e);
        }
    }

    @Override
    public List<Funcion> buscarFuncionesPorPelicula(ObjectId peliculaId) throws DaoException {
        try {

        if (peliculaId == null) {
            throw new IllegalArgumentException("El id de la película es obligatorio");
        }

        return coleccion.find(eq("peliculaId", peliculaId)).into(new ArrayList<>());

    } catch (MongoException e) {
        throw new DaoException(
                "No fue posible buscar las funciones por película",
                e
        );
    }
    }

    @Override
    public List<Funcion> buscarFuncionesPorFecha(LocalDate fecha) throws DaoException {
        try {

            if (fecha == null) {
                throw new IllegalArgumentException(
                        "La fecha es obligatoria"
                );
            }

            return coleccion.find(eq("fecha", fecha)).into(new ArrayList<>());

        } catch (MongoException e) {
            throw new DaoException(
                    "No fue posible buscar las funciones por fecha",
                    e
            );
        }
    }

    @Override
    public List<Funcion> buscarFuncionesPorPeliculaYFecha(ObjectId peliculaId, LocalDate fecha) throws DaoException {
        try {

            if (peliculaId == null) {
                throw new IllegalArgumentException(
                        "El id de la película es obligatorio"
                );
            }

            if (fecha == null) {
                throw new IllegalArgumentException(
                        "La fecha es obligatoria"
                );
            }

            return coleccion.find(
                    Filters.and(
                            Filters.eq("peliculaId", peliculaId),
                            Filters.eq("fecha", fecha)
                    )).into(new ArrayList<>());

        } catch (MongoException e) {
            throw new DaoException(
                    "No fue posible buscar las funciones",
                    e
            );
        }
    }

    @Override
    public boolean existeFuncionEnSalaHorario(Integer numSala, LocalDate fecha, LocalTime hora) throws DaoException {
        try {

            if (numSala == null) {
                throw new IllegalArgumentException(
                        "El número de sala es obligatorio"
                );
            }

            if (fecha == null) {
                throw new IllegalArgumentException(
                        "La fecha es obligatoria"
                );
            }

            if (hora == null) {
                throw new IllegalArgumentException(
                        "La hora es obligatoria"
                );
            }

            long coincidencias = coleccion.countDocuments(
                Filters.and(
                        Filters.eq("sala.numSala", numSala),
                        Filters.eq("fecha", fecha),
                        Filters.eq("hora", hora)
                    )
            );

            return coincidencias > 0;

        } catch (MongoException e) {
            throw new DaoException(
                    "No fue posible verificar la función",
                    e
            );
        }
    }
}
