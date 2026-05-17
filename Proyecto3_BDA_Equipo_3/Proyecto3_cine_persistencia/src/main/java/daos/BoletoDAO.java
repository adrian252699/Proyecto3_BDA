/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import config.ConexionMongo;
import embebidos.Asiento;
import entidades.Boleto;
import enums.EstadoBoleto;
import excepciones.daos.DaoException;
import interfaces.IBoletoDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class BoletoDAO implements IBoletoDAO{
    
    private final MongoCollection<Boleto> coleccion;

    public BoletoDAO() {
        this.coleccion = ConexionMongo.getCollection(
            "boletos",
            Boleto.class
        );
    }

    @Override
    public Boleto crearBoleto(Boleto boleto) throws DaoException {
        try {
            coleccion.insertOne(boleto);
            return boleto;
        } catch (MongoException e) {
            throw new DaoException("El asiento ya está ocupado para esta función");
        }
    }

    @Override
    public List<Boleto> obtenerPorFuncion(ObjectId funcionId) {
        return coleccion.find(Filters.eq("funcionId", funcionId)).into(new ArrayList<>());
    }

    @Override
    public void actualizarEstado(ObjectId boletoId, EstadoBoleto estado) throws DaoException {
        UpdateResult result = coleccion.updateOne(
                Filters.eq("_id", boletoId),
                Updates.set("estadoBoleto", estado)
        );

        if (result.getModifiedCount() == 0) {
            throw new DaoException("No se pudo actualizar el boleto");
        }
    }

    @Override
    public boolean asientoOcupado(ObjectId funcionId, Asiento asiento) {
        Bson filtro = Filters.and(
                Filters.eq("funcionId", funcionId),
                Filters.eq("asiento.fila", asiento.getFila()),
                Filters.eq("asiento.numero", asiento.getNumAsiento()),
                Filters.ne("estadoBoleto", EstadoBoleto.CANCELADO)
        );

        return coleccion.find(filtro).first() != null;
    }
    
}
