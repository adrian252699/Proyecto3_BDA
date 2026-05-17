/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Pago;
import enums.EstadoPago;
import excepciones.daos.DaoException;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public interface IPagoDAO {
    Pago crearPago(Pago pago) throws DaoException;
    void actualizarEstado(ObjectId pagoId, EstadoPago estado) throws DaoException;
    Pago obtenerPorBoleto(ObjectId boletoId)throws DaoException;
}
