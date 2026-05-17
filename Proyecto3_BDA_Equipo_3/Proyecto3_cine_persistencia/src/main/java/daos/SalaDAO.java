/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import config.ConexionMongo;
import embebidos.Asiento;
import embebidos.Sala;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import interfaces.ISalaDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author jalt2
 */
public class SalaDAO implements ISalaDAO{
    private final MongoCollection<Sala> coleccion;
    private final MongoCollection<Document> coleccionDoc;

    public SalaDAO() {
        this.coleccion = ConexionMongo.getCollection(
            "salas",
            Sala.class
        );
        
        this.coleccionDoc = ConexionMongo.getCollection(
                        "salas",
                        Document.class
        );
    }
    @Override
    public Sala crearSala(Sala sala) throws DaoException {
        try {
            if (sala==null) {
                throw new IllegalArgumentException("la sala no puede estar vacia");
            }
            
            this.coleccion.insertOne(sala);
            
            return sala;
        } catch (MongoException e) {
            throw new DaoException("Error al crear sala",e);
        }
    }

    @Override
    public Sala modificarSala(Sala salaModificar) throws DaoException,EntityNotFoundException {
        try {
            if (salaModificar == null || salaModificar.getId() == null) {
                throw new IllegalArgumentException("sala inválida");
            }
            
            UpdateResult resultado = coleccion.updateOne(
                eq("_id", salaModificar.getId()),
                combine(
                        set("numSala", salaModificar.getNumSala()),
                        set("capacidad",salaModificar.getCapacidad()),
                        set("asientos",salaModificar.getAsientos())    
                )
            );
            
            if (resultado.getMatchedCount() == 0) {
                throw new EntityNotFoundException("No se encontró la sala para actualizar");
            }
            
            return coleccion.find(eq("_id", salaModificar.getId())).first();
            
        } catch (MongoException e) {
            throw new DaoException("Error al actualizar sala",e);
        }
    }

    @Override
    public List<Sala> listarSalas() throws DaoException {
        try {
            
            List<Sala> listaSalas = coleccion.find().into(new ArrayList<>());
            
            if (listaSalas.isEmpty()) {
                return new ArrayList<>();
            }
            
            return listaSalas;
        } catch (MongoException e) {
            throw new DaoException("No fue posible listar las salas", e);
        }
    }

    @Override
    public List<Asiento> listarAsientosDisponibles(Integer numSala) throws DaoException {
        try {
            Document doc = coleccionDoc.aggregate(Arrays.asList(
                Aggregates.match(Filters.eq("numSala", numSala)),
                Aggregates.project(Projections.fields(
                    Projections.excludeId(),
                    Projections.computed(
                        "asientosDisponibles",
                        new Document("$filter", new Document()
                            .append("input", "$asientos")
                            .append("as", "a")
                            .append("cond",
                                new Document("$eq", Arrays.asList("$$a.disponible", true))
                            )
                        )
                    )
                ))
            )).first();

            if (doc == null || doc.get("asientosDisponibles") == null) {
                return new ArrayList<>();
            }

            List<Document> lista = doc.getList("asientosDisponibles", Document.class);

            List<Asiento> asientos = new ArrayList<>();

            for (Document d : lista) {
                asientos.add(new Asiento(
                    d.getString("fila"),
                    d.getInteger("numAsiento"),
                    d.getBoolean("disponible")
                ));
            }

            return asientos;
        } catch (MongoException e) {
            throw new DaoException("Error al obtener asientos disponibles", e);
        }
    }

    @Override
    public List<Asiento> listarAsientosOcupados(Integer numSala) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Sala buscarPorNumero(Integer numSala) throws DaoException, EntityNotFoundException {
        if (numSala==null) {
            throw new IllegalArgumentException("El numero de sala no puede estar vacio");
        }

        try {
            Sala sala = coleccion.find(eq("numSala", numSala)).first();

            if (sala == null) {
                throw new EntityNotFoundException("sala no encontrada");
            }

            return sala;
        } catch (MongoException e) {
           throw new DaoException("Error al buscar por numero de sala", e);
        }
    }
    
}
