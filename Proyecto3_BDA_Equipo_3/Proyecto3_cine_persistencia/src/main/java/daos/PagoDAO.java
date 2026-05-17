/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import config.ConexionMongo;
import entidades.Pago;
import enums.EstadoPago;
import excepciones.daos.DaoException;
import interfaces.IPagoDAO;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class PagoDAO implements IPagoDAO{

    private final MongoCollection<Pago> coleccion;

    public PagoDAO() {
        this.coleccion = ConexionMongo.getCollection("pagos", Pago.class);
    }

    @Override
    public Pago crearPago(Pago pago) throws DaoException {
        try {
            coleccion.insertOne(pago);
            return pago;
        } catch (MongoException e) {
            throw new DaoException("Error al crear Pago",e);
        }
    }

    @Override
    public void actualizarEstado(ObjectId pagoId, EstadoPago estado) throws DaoException {
        
        try {
            coleccion.updateOne(
                Filters.eq("_id", pagoId),
                Updates.set("estadoPago", estado)
            );
        } catch (MongoException e) {
            throw new DaoException("Error al actualizar estado del Pago",e);
        }  
    }

    @Override
    public Pago obtenerPorBoleto(ObjectId boletoId) throws DaoException{
        try {
            return coleccion.find(Filters.eq("boletoId", boletoId)).first();
        } catch (MongoException e) {
            throw new DaoException("Error al obtener por boleto",e);
        }
    }
    
}
